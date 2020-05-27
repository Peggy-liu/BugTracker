package com.bug.spring.security;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SSLConfig {

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint1 = new SecurityConstraint();
		        securityConstraint1.setUserConstraint("CONFIDENTIAL");
		        SecurityCollection collection = new SecurityCollection();
		        collection.addPattern("/*");
		        securityConstraint1.addCollection(collection);
		        context.addConstraint(securityConstraint1);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpToHttpsConnector());
		return tomcat;
	}
	@Bean
	public  Connector httpToHttpsConnector() {
		Connector result = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		result.setScheme("http");
		result.setPort(8080);
		result.setSecure(false);
		result.setRedirectPort(8443);
		return result;
	}
}
