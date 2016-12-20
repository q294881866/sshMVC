package org.jiumao.talentMarket.action;


import org.jiumao.login.LoginTerms;
import struts.ActionSupport;

public class LoginAction extends ActionSupport{

	/**
	 * 处理所有登录请求
	 * 通用的登录处理类
	 * @return
	 */
	public String login(){
		String content = super.request.getParameter("type");
		String userName = super.request.getParameter("userName");
		String password = super.request.getParameter("password");
		if (null == content) {
			content = "员工";
		}
		Object object = LoginTerms.doChoice(content)
				.doLogin(userName, password);
		request.getSession().setAttribute("object", object);
		return "login";
	}
	
	public String index(){
		return "index";
	}
}
