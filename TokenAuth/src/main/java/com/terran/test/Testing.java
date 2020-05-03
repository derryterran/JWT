//package com.terran.test;
//
//import com.terran.token.TerranAuthentication;
//
//import io.jsonwebtoken.Claims;
//
//public class Testing {
//	public static void main(String args[]){		
//	
//			String jwtId = "Terran33333";
//	        String jwtIssuer = "JWT Demo";
//	        String jwtSubject = "Andrew";
//	        String jwtPayload = "Terran Load";
//	        int jwtTimeToLive = 800000;
//	        TerranAuthentication t=new TerranAuthentication();
//	        String jwt = t.createJWT(
//	                jwtId,
//	                jwtIssuer,
//	                jwtSubject,
//	                jwtTimeToLive
//	        );
//	        
//	        System.out.println("jwt = \"" + jwt.toString() + "\"");
//
//	        Claims claims = t.decodeJWT(jwt);
//
//	        System.out.println("claims = " + claims.toString());
//	        System.out.println(claims.getId()+" "+claims.getIssuer()+" "+claims.get("username"));
//	}
//}
