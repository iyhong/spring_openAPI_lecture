package com.multi.oauth10client.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HttpServletBean;
import org.thinker.oauth.OAuthMsgConstants;
import org.thinker.oauth.RequestTokenVO;
import org.thinker.oauth.TokenSender;

import com.multi.oauth10client.Setup;

public class OAuth10RequestServlet extends HttpServletBean {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이곳에 코드를 작성합니다.
		try {
			RequestTokenVO vo = new RequestTokenVO();
			vo.setMethod("GET");
			vo.setRequestURL(Setup.REQUEST_TOKEN_URL);
			vo.setConsumerKey(Setup.CONSUMER_KEY);
			vo.setConsumerSecretKey(Setup.CONSUMER_SECRET);
			vo.setCallbackURL(Setup.CALLBACK_URL);
			vo.sign();
			TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
			sender.sendHttpClient(vo);
			String RT = vo.getRequestOauthToken();
			String RTS = vo.getRequestOauthTokenSecret();
			HttpSession session = req.getSession();
			session.setAttribute("RT", RT);
			session.setAttribute("RTS", RTS);
			resp.sendRedirect(Setup.AUTHORIZE_URL + "?" + OAuthMsgConstants.OAUTH_TOKEN + "=" + RT);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
