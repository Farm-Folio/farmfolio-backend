package com.farm.farmfolio.config;

import com.keycloak.connector.security.IAMKeycloakSecurityConfig;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

//@Configuration
//@Order(1)
public class FarmFolioSecurityConfig  {
    private static final String RESOURCES = "/resources/**";
    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String API_DOCS = "/api-docs/**";


//    public FarmFolioSecurityConfig(KeycloakClientRequestFactory keycloakClientRequestFactory) {
//        super(keycloakClientRequestFactory);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(RESOURCES, SWAGGER_UI_HTML, SWAGGER_UI, API_DOCS, "/otp/send-otp", "/user/by-mobile", "/user/login", "/user/save",
//                //"/unsecure/token", "/users/save-device", "/users/signin", "/mobile-user/unsecure/**","/report/**");
//                "/unsecure/**", "/users/save-device", "/unsecure/access/token" ,"/users/signin", "/mobile-user/unsecure/**", "/report/**", "/file/**");
//    }
}
