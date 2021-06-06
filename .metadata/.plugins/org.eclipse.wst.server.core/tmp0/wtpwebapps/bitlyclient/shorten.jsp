<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.opensg.oauth2.client.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
  if (session.getAttribute("access_token") == null) {
    throw new Exception("access_token is null!!");  
  }

  String access_token = (String)session.getAttribute("access_token");
  
  String jsonRequest = "{ \"long_url\" : \""+ request.getParameter("long_url") + "\" } ";
    StringRequestEntity requestEntity = 
            new StringRequestEntity(jsonRequest, "application/json", "UTF-8");
      
  String bearerToken = OAuth2ClientUtil.generateBearerTokenHeaderString(access_token);
  String endpoint = Settings.SHORTEN_API_URL;
  HttpClient client = new HttpClient();
  client.getParams().setContentCharset("utf-8");
  
  PostMethod method = new PostMethod(endpoint);
  method.setRequestEntity(requestEntity);
  method.setRequestHeader("Authorization", bearerToken);
  method.setRequestHeader("Content-type", "application/json");
  method.setRequestHeader("Accept", "application/json");
  
  int status = client.executeMethod(method);
  String result= "";
  if (status >= 200 || status < 300) {
    result = method.getResponseBodyAsString();
  } else {
    result = "{ \"status\" : \"오류 발생 : " + status + "\" }";
  }  
%>
<%=result %>
