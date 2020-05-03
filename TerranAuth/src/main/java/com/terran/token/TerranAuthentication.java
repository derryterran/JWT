package com.terran.token;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
public class TerranAuthentication {
	
	private static String SECRET_KEY = "secret";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String createJWT(Map mapValue) {
		long ttlMillis=80000;
		String id=UUID.randomUUID().toString();
		String issuer="PN123";
		String subject="Payment Network";
		String audience="CIMB";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        
        
        JwtBuilder builder = Jwts.builder()
        		.setClaims(mapValue)
        		.setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
