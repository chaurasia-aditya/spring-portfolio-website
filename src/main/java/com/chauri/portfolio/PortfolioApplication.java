package com.chauri.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<ClearSessionFilter> clearSessionFilter() {
//		FilterRegistrationBean<ClearSessionFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setFilter(new ClearSessionFilter());
//		registrationBean.addUrlPatterns("/*");
//		return registrationBean;
//	}

	private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.addDialect(new Java8TimeDialect());
		engine.setTemplateResolver(templateResolver);
		return engine;
	}
}
