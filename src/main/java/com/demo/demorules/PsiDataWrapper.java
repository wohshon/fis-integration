package com.demo.demorules;

import com.google.gson.annotations.SerializedName;

public class PsiDataWrapper {

	@SerializedName("com.demo.demorules.PsiData")
	private PsiData psiDate=new PsiData();

	public PsiData getPsiDate() {
		return psiDate;
	}

	public void setPsiDate(PsiData psiDate) {
		this.psiDate = psiDate;
	}
	
	
	
}
