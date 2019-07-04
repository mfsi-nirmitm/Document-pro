package com.documentpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.documentpro.dao"})
@ComponentScan({"com.documentpro"})
@EnableJpaAuditing(setDates = true)
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
