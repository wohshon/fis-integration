package com.test;

import com.demo.demorules.BrmsInsertElement;
import com.demo.demorules.BrmsPayload;
import com.demo.demorules.FireRulesObject;
import com.demo.demorules.InsertElementWrapper;
import com.demo.demorules.PsiData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {
	public static void main(String[] args) {
		
		//Gson gson=new Gson();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		BrmsInsertElement insert=new BrmsInsertElement();
		InsertElementWrapper insertWrapper=new InsertElementWrapper();
		insertWrapper.setInsert(insert);
		FireRulesObject fireRulesObject=new FireRulesObject();
		PsiData psi=new PsiData();
		psi.setReading(1000);
		insert.getObject().setPsiDate(psi);
		BrmsPayload brms=new BrmsPayload(insertWrapper,fireRulesObject);
		System.out.println(gson.toJson(brms));
		
	}
}
