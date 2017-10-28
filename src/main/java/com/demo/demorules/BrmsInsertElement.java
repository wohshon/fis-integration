package com.demo.demorules;

import com.google.gson.annotations.SerializedName;

public class BrmsInsertElement {
	@SerializedName("out-identifier")
	private String outidentifier="result";
	//private PsiData object=new PsiData();
	private PsiDataWrapper object=new PsiDataWrapper();
	@SerializedName("return-object")
	private boolean returnObject=true;
	@SerializedName("entry-point")
	private String entryPoint="DEFAULT";
	public String getOutidentifier() {
		return outidentifier;
	}
	public void setOutidentifier(String outidentifier) {
		this.outidentifier = outidentifier;
	}
	public PsiDataWrapper getObject() {
		return object;
	}
	public void setObject(PsiDataWrapper object) {
		this.object = object;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	
	
	
}
