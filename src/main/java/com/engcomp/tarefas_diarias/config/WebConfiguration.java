package com.engcomp.tarefas_diarias.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("https://oficinaapp-frontend.onrender.com")
					.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true);
		
	}

}
