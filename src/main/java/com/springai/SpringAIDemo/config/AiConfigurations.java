package com.springai.SpringAIDemo.config;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfigurations {
	
	@Bean
	public OllamaApi ollamaApi(){
		return new OllamaApi("http://localhost:11434");
	}

}
