package com.GateWay.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.GateWay.JwtClass.JwtClass;
import com.GateWay.routeValidate.RouteValidator;
import com.google.common.net.HttpHeaders;

@Component
public class ConfigFilter extends AbstractGatewayFilterFactory<ConfigFilter.Config> {

	@Autowired
	public RouteValidator routeValidator;

	@Autowired
	public RestTemplate restTemplate;
	@Autowired
	public JwtClass jwtclass;

	public ConfigFilter() {
		super(Config.class);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @Override public GatewayFilter apply(Config config) {
	 * System.out.println("Inside gateway"); return ((exchange, chain) ->{ //
	 * ServerHttpRequest request = exchange.getRequest();
	 * System.out.println("in side return"); if
	 * (routeValidator.isSecured.test((org.springframework.http.server.
	 * ServerHttpRequest) exchange.getRequest())) {
	 * //System.out.println("in side if");
	 * 
	 * if
	 * (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
	 * {
	 * 
	 * throw new RuntimeException("Missing authorization"); }
	 * 
	 * String
	 * authheader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).
	 * get(0);
	 * 
	 * if (authheader!=null && authheader.startsWith("Bearer ")) {
	 * 
	 * authheader = authheader.substring(7); }
	 * 
	 * try { //restTemplate.getForObject("http://IDENTITY-SERVICE/validate?token"+
	 * authheader, String.class);
	 * 
	 * jwtclass.validateToken(authheader); }catch (Exception e) { // TODO: handle
	 * exception throw new RuntimeException("unauthorise access"); } }
	 * 
	 * return chain.filter(exchange);
	 * 
	 * });
	 * 
	 * }------
	 */

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			
			 org.springframework.http.server.reactive.ServerHttpRequest request = exchange.getRequest();
			if (routeValidator.isSecured.test(request)) { // Use ServerHttpRequest directly
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing token");
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				try {
					jwtclass.validateToken(authHeader);
				} catch (Exception e) {
					throw new RuntimeException("Unauthorized");
				}
			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
