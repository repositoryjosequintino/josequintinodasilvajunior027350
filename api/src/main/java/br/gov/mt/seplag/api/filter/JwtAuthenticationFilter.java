package br.gov.mt.seplag.api.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.gov.mt.seplag.api.utility.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
    private JwtUtility jwtUtility;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		final String authorizationHeader = response.getHeader("Authotization");
		
		String identificador = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				jwt = authorizationHeader.substring(7);
				identificador = JwtUtility.extractIdentificadorToken(jwt);
			} catch (Exception e) {
				logger.error("Erro ao manipular JWT: " + e.getMessage());
			}
		}
		
		if (identificador != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (jwtUtility.validateToken(jwt, identificador) && jwtUtility.isAccessToken(jwt)) {
				String perfil = jwtUtility.extractPerfilToken(jwt);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(identificador,
						null, 
						Collections.singleton(new SimpleGrantedAuthority("ROLE_" + perfil)));
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);
		
	}

}
