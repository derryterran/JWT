package com.token.util;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class TokenController {
	public Logger logger = Logger.getLogger("terranLog");
	 @RequestMapping("/terranrest/servicesed/{alias}")  
	    public String testing(@Context HttpServletRequest requestContext,@RequestBody String value,@PathVariable("alias") String alias){ 
		 return "abcdEFG";
	 }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/encrypt")
	public String encrypt(@Context HttpServletRequest requestContext,@RequestBody String value) {
		Map mpReturn=new HashMap();
		try {
			Map map=getTerranServicesJSonEngine(value);
	        String jwt =TokenUtil.TokenEncrypt (map);
	        mpReturn.put("serial", jwt.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return convertJson(mpReturn);
	}
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/decrypt")
	public String decrypt(@Context HttpServletRequest requestContext,@RequestBody String value) {
		Map mpReturn=new HashMap();
		try {
			Map map=getTerranServicesJSonEngine(value);
			mpReturn=TokenUtil.TokenDecrypt(map.get("serial").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return convertJson(mpReturn);
	}
	@SuppressWarnings("rawtypes")
	private Map getTerranServicesJSonEngine( String param) throws ParseException {
		logger.info("Call service : "  + " : " + param);
		Map mapFromJson = null;
		
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(param);
			Gson gsonx = new Gson();
			mapFromJson = gsonx.fromJson(jsonObject.toJSONString(), Map.class);
		
		return mapFromJson;
		
	}
	@SuppressWarnings("rawtypes")
	public String convertJson(Map map){
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
			try {
				json = objectMapper.writeValueAsString(map);
				json = String.valueOf(json);
				String s = json;
				s = "" + s;
				s = s.replaceAll("\\\\", "");
				return s;
			} catch (Exception e) {
				return "TerranService :Failed to processing data :"
						+ e.toString();
			}
	}
}
