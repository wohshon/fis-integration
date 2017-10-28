package com.demo.demorules;

import com.google.gson.annotations.SerializedName;

public class BrmsPayload {
	private String lookup="defaultKieSession";
	private Object[] commands=new Object[2];
	
	
	public BrmsPayload(InsertElementWrapper insertElementWrapper, FireRulesObject fireRulesObject) {
//		BrmsInsertElement insert=new BrmsInsertElement();
		InsertElementWrapper insert=insertElementWrapper;
		//PsiDataWrapper data=new PsiDataWrapper();
		//insert.getInsert().setObject(data);
		FireRulesObject fireallrules=fireRulesObject;
		this.commands[0]=insert;
		this.commands[1]=fireallrules;
	}
	public Object[] getCommands() {
		return commands;
	}
	public void setCommands(Object[] commands) {
		this.commands = commands;
	}

	
}
