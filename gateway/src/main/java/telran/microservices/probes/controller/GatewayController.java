package telran.microservices.probes.controller;

import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.microservices.probes.service.ProxyService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GatewayController {
	final ProxyService proxyService;
	@GetMapping("/**")
	ResponseEntity<byte[]> getResult(ProxyExchange<byte[]> proxy, HttpServletRequest request) {
		log.trace("received URI {}; received query string {}", request.getRequestURI(), request.getQueryString());
		return proxyService.proxyRouting(proxy, request);
	}
}