package com.multi.oauth10client.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HttpServletBean;
import org.thinker.oauth.AccessTokenVO;
import org.thinker.oauth.TokenSender;

import com.multi.oauth10client.Setup;

public class OAuth10CallbackServlet extends HttpServletBean {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이곳에 코드를 작성합니다.
		try {
			HttpSession session = req.getSession();
			String RT = (String) session.getAttribute("RT");
			String RTS = (String) session.getAttribute("RTS");
			AccessTokenVO vo = new AccessTokenVO(req.getQueryString());
			vo.setMethod("GET");
			vo.setRequestURL(Setup.ACCESS_TOKEN_URL);
			vo.setConsumerKey(Setup.CONSUMER_KEY);
			vo.setConsumerSecretKey(Setup.CONSUMER_SECRET);
			vo.setRequestOauthToken(RT);
			vo.setRequestOauthTokenSecret(RTS);
			vo.sign();

			TokenSender sender = new TokenSender();
			sender.sendHttpClient(vo);
			session.setAttribute("AT", vo.getRequestOauthToken());
			session.setAttribute("ATS", vo.getRequestOauthTokenSecret());
			resp.sendRedirect("resource");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
