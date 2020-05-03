package com.token.util;
import java.util.HashMap;
import java.util.Map;

import com.terran.token.TerranAuthentication;

import io.jsonwebtoken.Claims;
public class TokenUtil {
	@SuppressWarnings("static-access")
	public static String TokenEncrypt(Map<String,String> mapValue)
	{
		TerranAuthentication t=new TerranAuthentication();
		String jwt= t.createJWT(mapValue);
	     return jwt;
	}
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public static Map TokenDecrypt(String jwt)
	{
		Map mapVal=new HashMap();
		TerranAuthentication t=new TerranAuthentication();
		Claims claims = t.decodeJWT(jwt);
		mapVal.put("issuer", claims.getIssuer());
		mapVal.put("subject", claims.getSubject());
		mapVal.put("id", claims.getId());
		mapVal.put("date", claims.getExpiration());
		return mapVal;
	}
}


