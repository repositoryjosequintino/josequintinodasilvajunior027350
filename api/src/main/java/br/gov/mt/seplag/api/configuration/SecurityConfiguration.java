package br.gov.mt.seplag.api.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.gov.mt.seplag.api.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private static final String PUBLIC_ENDPOINT[] = {
			"/api/v1/usuario/registrar",
			"/api/v1/autenticador/usuario/registrar",
			"/api/v1/autenticador/acessar",
            "/api/v1/autenticador/verificar-conta",
            "/api/v1/autenticador/reenviar-email",
            "/api/v1/autenticador/redefinir-senha"
	};
	
	private static final String VERBOS_HTTP[] = {
		"GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
	};
	
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
	
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
			corsConfiguration.setAllowedMethods(Arrays.asList(VERBOS_HTTP));
			corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
			corsConfiguration.setAllowedOrigins(List.of("*"));
			UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
