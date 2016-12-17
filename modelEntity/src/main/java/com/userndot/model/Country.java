package com.userndot.model;

import java.util.Currency;

public class Country {

	private int countryID;
	private String name;
	private int isdCode;
	private Language language;
	private Currency currency;
	@Override
	public String toString() {
		return "Country [countryID=" + countryID + ", name=" + name
				+ ", isdCode=" + isdCode + ", language=" + language
				+ ", currency=" + currency + "]";
	}
	
	
}
