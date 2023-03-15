package com.farm.farmfolio.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
@Order(0)
public class TenantSelectionFilter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
			throws IOException {
		String requestURI = request.getRequestURI();
		String tenantID = request.getHeader("X-TenantID");
		TenantContext.setCurrentTenant(tenantID);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) {
		TenantContext.clear();
	}
}
