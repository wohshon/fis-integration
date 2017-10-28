package com.demo.demorules;

import com.google.gson.annotations.SerializedName;

public class FireRulesObject {

	@SerializedName("fire-all-rules")
	Object fireallrules=new Object();

	public Object getFireallrules() {
		return fireallrules;
	}

	public void setFireallrules(Object fireallrules) {
		this.fireallrules = fireallrules;
	}
	
}
