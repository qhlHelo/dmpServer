package com.chinagreentown.dmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DmpServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // 设置windows系统下hadoop环境
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
        SpringApplication.run(DmpServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(DmpServerApplication.class);
    }


    //	@Component
//	public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {
//
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			container.setPort(8888);//1
//			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//			container.setSessionTimeout(10, TimeUnit.MINUTES);
//		}
//
//	}
//
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//		factory.setPort(8888);
//		factory.setSessionTimeout(10, TimeUnit.MINUTES);
//		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//		return factory;
//	}
//
//
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//
//		tomcat.addAdditionalTomcatConnectors(httpConnector());
//		return tomcat;
//	}
//
//	@Bean
//	public Connector httpConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8080);
//		connector.setSecure(false);
//		connector.setRedirectPort(8443);
//		return connector;
//	}
}
