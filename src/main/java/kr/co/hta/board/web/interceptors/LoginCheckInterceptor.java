package kr.co.hta.board.web.interceptors;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	private Set<String> urls;
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//컨트롤러 실행 전에 실행
		String requestURI = request.getRequestURI();

		if(urls.contains(requestURI)) {
			return true;//컨트롤러 실행
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("LOGIN_USER") != null) {
			return true;
		}
		response.sendRedirect("/user/login.do?err=deny");
		return false;//컨트롤러 실행 안됨
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//컨트롤러 실행 후에 실행
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//컨트롤러 실행되면 상시실행
		
	}
}
