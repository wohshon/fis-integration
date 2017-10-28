package com.demo.processor;

import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.demorules.BrmsInsertElement;
import com.demo.demorules.BrmsPayload;
import com.demo.demorules.FireRulesObject;
import com.demo.demorules.InsertElementWrapper;
import com.demo.demorules.PsiData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BrmsBean {
	Logger log=LoggerFactory.getLogger(getClass());

	public String prepareQuery(String body, Exchange exchg) {
		log.info(body);
		JsonParser parser=new JsonParser();
		JsonObject value=parser.parse(body).getAsJsonObject();
		log.info(value.get("psi").getAsString());		
		int reading=Integer.valueOf(value.get("psi").getAsString());
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		BrmsInsertElement insert=new BrmsInsertElement();
		InsertElementWrapper insertElementWrapper=new InsertElementWrapper();
		insertElementWrapper.setInsert(insert);
		FireRulesObject fireRulesObject=new FireRulesObject();
		PsiData psi=new PsiData();
		psi.setReading(reading);
		insert.getObject().setPsiDate(psi);
		BrmsPayload brms=new BrmsPayload(insertElementWrapper,fireRulesObject);
		String payload=new Gson().toJson(brms);		
		return payload;
	}
	
	public String extractValue(String body, Exchange exchg) {
		
		log.info(body);
		JsonParser parser=new JsonParser();
		/*String value=parser.parse(body)
				.getAsJsonObject().get("result")
				.getAsJsonObject().get("execution-results")
				.getAsJsonObject().getAsJsonArray("results").get(0)
				.getAsJsonObject().get("value").getAsJsonObject()
				.getAsJsonObject().get("com.demo.demorules.PsiData")
				.getAsJsonObject().get("status")
				.getAsString();*/
		JsonObject data=parser.parse(body)
				.getAsJsonObject().get("result")
				.getAsJsonObject().get("execution-results")
				.getAsJsonObject().getAsJsonArray("results").get(0)
				.getAsJsonObject().get("value").getAsJsonObject()
				.getAsJsonObject().get("com.demo.demorules.PsiData")
				.getAsJsonObject();
		
		String status=data.get("status")!=JsonNull.INSTANCE?data.get("status").getAsString():"";
		String notify=data.get("notify")!=JsonNull.INSTANCE?data.get("notify").getAsString():"";
		String notifyType=data.get("notifyType")!=JsonNull.INSTANCE?data.get("notifyType").getAsString():"";		
		exchg.getIn().setHeader("PSI_STATUS", status);
		exchg.getIn().setHeader("NOTIFY_FLAG", notify);
		exchg.getIn().setHeader("NOTIFY_TYPE", notifyType);
			log.info(status);		
	return status;
	}
}
