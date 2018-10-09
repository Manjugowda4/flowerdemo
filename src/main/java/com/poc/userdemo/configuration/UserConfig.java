package com.poc.userdemo.configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserConfig {

	@Value("${proxy.host}")
	private String proxyHost;
	
	@Value("${proxy.port}")
	private int proxyPort;
	
	@Value("${proxy.enabled}")
	private boolean proxyEnabled;
	
	@Value("${connection.timeout}")
	private int connectionTimeout;
	
	@Value("${read.timeout}")
	private int readTimeout;
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		// Set JSON converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		
		// Set connection and Read time outs
		SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		requestFactory.setConnectTimeout(connectionTimeout);
		requestFactory.setReadTimeout(readTimeout);
		
		// Set Proxy details if enabled
		if (proxyEnabled) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
			requestFactory.setProxy(proxy);
		}
		
		return restTemplate;
	}
	
}
