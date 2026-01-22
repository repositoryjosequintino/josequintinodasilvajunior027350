package br.gov.mt.seplag.api.utility;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
	
	@Value("${jwt.secret}")
    private String segredo;
	
	@Value("${jwt.access.token.expiration}")
	private Long accessTokenExpiration;
	
	@Value("${jwt.refresh.token.expiration}")
	private Long refreshTokenExpiration;
	
	private final static String TYPE_TOKEN_ACCESS = "ACCESS";
	
	private final static String TYPE_TOKEN_REFRESH = "REFRESH";
	
	private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));
    }
	
	public String generateAccessToken(Long code, String nome, String identificador, String perfil) {
		Map<String, Object> claimMap = new HashMap<>();
			claimMap.put("code", code);
			claimMap.put("code", nome);
			claimMap.put("identificador", identificador);
			claimMap.put("perfil", perfil);
			claimMap.put("type", TYPE_TOKEN_ACCESS);
		return createToken(claimMap, identificador, accessTokenExpiration);
	}
	
	public String generateRefreshToken(Long code, String identificador) {
		Map<String, Object> claimMap = new HashMap<>();
			claimMap.put("code", code);
			claimMap.put("identificador", identificador);
			claimMap.put("type", TYPE_TOKEN_REFRESH);
	return createToken(claimMap, identificador, refreshTokenExpiration);
	}
	
	public String createToken(Map<String, Object> claimMap, String subject, Long accessTokenExpiration) {
		Date now = new Date();
		Date expiry = new Date(new Date().getTime() + accessTokenExpiration);
		return Jwts.builder()
                .claims(claimMap)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
	}
	
	public Long extractCodeToken(String token) {
		return extractClaim(token, claims -> claims.get("code", Long.class));
	}
	
	public String extractNomeToken(String nome) {
		return extractClaim(nome, claims -> claims.get("nome", String.class));
	}
	
	public String extractIdentificadorToken(String identificador) {
		return extractClaim(identificador, claims -> claims.get("identificador", String.class));
	}
	
	public String extractPerfilToken(String perfil) {
		return extractClaim(perfil, claims -> claims.get("perfil", String.class));
	}
	
	public String extractTipoTokenToken(String tipo) {
		return extractClaim(tipo, claims -> claims.get("tipo", String.class));
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <R> R extractClaim(String token, Function<Claims, R> claimFunction) {
		final Claims claims = getAllClaimsFromToken(token);
        return claimFunction.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
	
	private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
	
	public Boolean validateToken(String token, String identificador) {
		final String extractedIdentificador = extractIdentificadorToken(identificador);
		return (extractedIdentificador.equals(identificador) && !isTokenExpired(token));
	}
	
	public Boolean isAccessToken(String token) {
		return TYPE_TOKEN_ACCESS.equals(extractTipoTokenToken(token));
	}
	
	public Boolean isRefreshToken(String token) {
		return TYPE_TOKEN_REFRESH.equals(extractTipoTokenToken(token));
	}

}
