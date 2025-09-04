package com.engcomp.tarefas_diarias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("https://oficinaapp-frontend.onrender.com") // Substitua pelo URL do seu front-end
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
					.allowedHeaders("*") // Cabeçalhos permitidos
					.allowCredentials(true); // Muito importante: permite o envio de credenciais (cookies, headers, etc.)


	}

}
