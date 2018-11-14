package com.test.qa.data;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String searchKeyWord;
	private String proudctName;
	private String beforeValue;
	private String afterValue;
	
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public String getProudctName() {
		return proudctName;
	}
	public void setProudctName(String proudctName) {
		this.proudctName = proudctName;
	}
	
	
}
