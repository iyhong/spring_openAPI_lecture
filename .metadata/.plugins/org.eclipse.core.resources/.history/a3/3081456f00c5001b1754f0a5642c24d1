<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.opensg.oauth2.client.*" %>
<%@ page import="java.util.*" %>
<%
  HashMap<String,String> map = new HashMap<String, String>();
  map.put("client_id", Settings.CLIENT_ID);
  map.put("redirect_uri", Settings.REDIRECT_URI);
  map.put("response_type", "code");
  String url = Settings.AUTHORIZE_URL + "?" + 
      OAuth2ClientUtil.getParamStringFromMap(map);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>bitly Oauth 2.0 client Test</title>
</head>
<body>
  <a href="<%=url%>">bitly 앱 승인 페이지로 이동</a>
  <br /><br /><br />
  <p>반드시 http://jcornor.com:8000/bitlyclient 로 실행하세요.</p>
</body>
</html>