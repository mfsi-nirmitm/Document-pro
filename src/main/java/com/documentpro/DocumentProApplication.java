package com.documentpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.documentpro.config.JwtFilter;

@SpringBootApplication
public class DocumentProApplication {

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new JwtFilter());
//		registrationBean.addUrlPatterns("/rest/*");
//		return registrationBean;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(DocumentProApplication.class, args);
	}

}
