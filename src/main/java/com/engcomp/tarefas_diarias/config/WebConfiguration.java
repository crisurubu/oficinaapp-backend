package com.engcomp.tarefas_diarias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("https://oficinaapp-frontend.onrender.com")
					.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(false);
		
	}

}
