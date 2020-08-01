// package com.example.sampleproject.config;

// import org.apache.catalina.Container;
// import org.apache.catalina.Host;
// import org.apache.catalina.WebResourceRoot;
// import org.apache.catalina.WebResourceRoot.ResourceSetType;
// import org.apache.catalina.core.StandardContext;
// import org.apache.catalina.startup.Tomcat;
// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
// import org.springframework.boot.web.server.WebServer;
// import org.springframework.boot.web.servlet.ServletContextInitializer;
// import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class TomcatConfigration {
//     @Bean
//     public ServletWebServerFactory serverSettings() {
//         TomcatServletWebServerFactory factory = new MyTomcatServletWebServerFactory();
//         return factory;
//     }

//     static class MyTomcatServletWebServerFactory extends TomcatServletWebServerFactory {
//     	@Override
//     	public WebServer getWebServer(ServletContextInitializer... initializers) {
//     		TomcatWebServer ret = (TomcatWebServer) super.getWebServer(initializers);

//     		Tomcat tomcat = ret.getTomcat();
//     		Host host = tomcat.getHost();
//     		Container[] findChildren = host.findChildren();
//     		StandardContext context = (StandardContext) findChildren[0];
//     		WebResourceRoot webResourceRoot = context.getResources();
//     		webResourceRoot.createWebResourceSet(ResourceSetType.PRE,
//     				"/", "frontend/build", null,  "/");
//     		return ret;
//     	}
//     }
// }