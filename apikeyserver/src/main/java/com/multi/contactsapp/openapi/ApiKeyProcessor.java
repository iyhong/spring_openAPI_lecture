package com.multi.contactsapp.openapi;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class ApiKeyProcessor {

	private static final String API_KEY_PROPERTIES = "apiKey.properties";
	private static final String MAX_COUNT = "max";

	private static int maxCount;

	private Properties prop;

	@Autowired
	private ApiKeyRepository repository;

	public ApiKeyProcessor() throws ApiKeyException {

		this(ApiKeyProcessor.class.getResource(API_KEY_PROPERTIES));
	}

	public ApiKeyProcessor(URL url) throws ApiKeyException {

		prop = new Properties();

		try {
			prop.load(url.openStream());
			maxCount = Integer.parseInt(prop.getProperty(MAX_COUNT));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiKeyException("Could not find API KEY FILE", "E001");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiKeyException(e.getMessage());
		}
	}

	public String requestNewAPIKey(ApiKeyVO apiKeyVO) throws Exception {
		
		//아래의 return 문을 삭제하고 코드를 작성합니다.
		String apiKey = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
		
		apiKeyVO.setApiKey(apiKey);
		try {
			repository.create(apiKeyVO);
		}catch(Exception e) {
			throw new ApiKeyException("동일한 API KEY가 존재합니다");
		}
		return apiKey;
	}

	public void checkApiKey(String hostname, String apiKey) throws ApiKeyException {
	
		//이곳에 코드를 작성합니다.
		ApiKeyVO vo = repository.read(apiKey);
		if(vo == null) {
			throw new ApiKeyException("등록되지 않은 API KEY");
		}
		
		if(hostname == null || hostname.equals(vo.getHostName()) == false) {
			throw new ApiKeyException("Origin이 일치하지 않음");
		}
		
		if(vo.getCount() >= maxCount) {
			throw new ApiKeyException("사용량 제한 초과");
		}
	
		repository.update(apiKey);
	}

}
