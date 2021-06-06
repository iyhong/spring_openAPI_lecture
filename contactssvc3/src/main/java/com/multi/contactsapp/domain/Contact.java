package com.multi.contactsapp.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="연락처 한 건 객체")
@JacksonXmlRootElement(localName = "contact")
public class Contact {
  @JacksonXmlProperty(isAttribute = true)
  @ApiModelProperty(value="일련번호", example="1004")
  private long no;
  @ApiModelProperty(value="이름", example="홍길동")
  private String name;
  @ApiModelProperty(value="전화번호", example="010-2222-3331")
  private String tel;
  @ApiModelProperty(value="주소", example="서울시")
  private String address;
	
	//생성자2개 getter, setter toString()
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contact(long no, String name, String tel, String address) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.address = address;
	}
	

	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Contact [no=" + no + ", name=" + name + ", tel=" + tel + ", address=" + address + "]";
	}
	
	
	
}
