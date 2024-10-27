package com.sc.servicecompanies.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import com.sc.servicecompanies.config.security.filter.JwtAuthenticationFilter;
import com.sc.servicecompanies.config.security.authorization.CorsConfig;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider daoAuthProvider;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) // Enable CORS
            .csrf(csrfConfig -> csrfConfig.disable())
            .sessionManagement(sessMagConfig -> 
                sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(daoAuthProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authReqConfig -> {
                authReqConfig
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // Allow CORS preflight requests
                    .requestMatchers("/auth/authenticate", "/auth/validate-token").permitAll() // Allow authentication endpoints
                    .requestMatchers("/auth/logout").permitAll()
                    .requestMatchers("/customers").permitAll() // Allow public access to /customers
                    .requestMatchers("/api/supply").permitAll()
                    .anyRequest().access(authorizationManager); // Apply authorization manager to other requests
            })
            .exceptionHandling(exceptionConfig -> {
                exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
                exceptionConfig.accessDeniedHandler(accessDeniedHandler);
            })
            .build();
    }

    // private static void buildRequestMatchers(
    //                 AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
    //         authReqConfig.requestMatchers(HttpMethod.GET, "/products")
    //                         .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

    //         // authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
    //         // .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

    //         authReqConfig.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "/products/[0-9]*"))
    //                         .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

    //         authReqConfig.requestMatchers(HttpMethod.POST, "/products")
    //                         .hasRole(RoleEnum.ADMINISTRATOR.name());

    //         authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
    //                         .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

    //         authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
    //                         .hasRole(RoleEnum.ADMINISTRATOR.name());

    //         authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
    //         authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
    //         authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

    //         authReqConfig.anyRequest().authenticated();
    // }

}