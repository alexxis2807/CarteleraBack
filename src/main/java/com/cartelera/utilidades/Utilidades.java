/* package com.cartelera.utilidades;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



public class Utilidades {
    public static String getJWTToken(String contrasena, String secretKey) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

				Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

				String token = Jwts
					.builder()
					.setId("carteleraJWT")
					.setSubject(contrasena)
					.claim("authorities",
							grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(key)
					.compact();
				
				return "Bearer " + token;
	}
}
 */