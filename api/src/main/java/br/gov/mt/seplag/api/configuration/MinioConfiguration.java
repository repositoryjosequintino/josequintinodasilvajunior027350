package br.gov.mt.seplag.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfiguration {
	
	@Bean
	public MinioClient minioConfigurationClient() {
		return MinioClient.builder()
				.endpoint("http://localhost:9000")
				.credentials("minioadmin", "minioadmin")
				.build();
	}

}
