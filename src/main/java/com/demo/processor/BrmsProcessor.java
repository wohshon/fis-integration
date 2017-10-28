package com.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.demorules.BrmsInsertElement;
import com.demo.demorules.BrmsPayload;
import com.demo.demorules.FireRulesObject;
import com.demo.demorules.InsertElementWrapper;
import com.demo.demorules.PsiData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BrmsProcessor implements Processor {

	Logger log=LoggerFactory.getLogger(getClass());
	@Override
	public void process(Exchange exchg) throws Exception {
		String output=new String((byte[])exchg.getIn().getBody());
		log.info(output);
		JsonParser parser=new JsonParser();
		JsonObject value=parser.parse(output).getAsJsonObject();
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
		log.info(payload);
		exchg.getIn().setBody(payload);
		
	}

}
