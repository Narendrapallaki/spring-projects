package com.GateWay.routeValidate;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class RouteValidator {
	
	public static final List<String> openApiEndpoints=List.of(
			"/security/register",
			"/security/token",
			"/eureka"
			);
	
//	public Predicate<ServerWebExchange> isSecured=
//			request-> openApiEndpoints.stream().noneMatch(uri->request.geturl().getPath().contains(uri));

//			public Predicate<ServerWebExchange> isSecured = new Predicate<ServerWebExchange>() {
//			    @Override
//			    public boolean test(ServerWebExchange exchange) {
//			        return openApiEndpoints.stream().noneMatch(uri -> exchange.getRequest().getURI().getPath().contains(uri));
//			    }
//			};
	public Predicate<ServerHttpRequest> isSecured = request ->
    openApiEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
