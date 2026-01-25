package br.gov.mt.seplag.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfiguration {
	
	@Bean
	public MinioClient mionioClient() {
		return MinioClient.builder()
				.endpoint("http://localhost:9001")
				.credentials("minioadmin", "minioadmin")
				.build();
	}

}
