package com.farm.farmfolio.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class HeaderFilter implements HandlerInterceptor {

    @Override
	public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object object)  throws IOException{
		String serialNo = request.getHeader(com.farm.farmfolio.config.Constants.SERIAL_NO);
		String version = request.getHeader(com.farm.farmfolio.config.Constants.VERSION_NO);
		Map<String, String> headers = new HashMap<>();
		headers.put("serialNo", serialNo);
		headers.put("version", version);
		HeaderContext.setCurrentHeader(headers);
		return true;
	}
}
