package telran.microservices.probes.service;

import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface ProxyService {
	ResponseEntity<byte[]> proxyRouting(ProxyExchange<byte[]> proxy, HttpServletRequest request);
}
