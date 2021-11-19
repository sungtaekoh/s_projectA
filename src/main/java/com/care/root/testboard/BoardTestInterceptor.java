package com.care.root.testboard;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.MemberSessionName;

public class BoardTestInterceptor extends HandlerInterceptorAdapter
											implements MemberSessionName{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) == null) {
			//response.sendRedirect("login");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인 해라!!!');"
					//+ " location.href='/root/member/login'; </script>");
	+ " location.href='"+ request.getContextPath() +"/member/login'; </script>");
			return false;
		} 
		return true;
	}

	
}
