package org.gradle;

public enum Currency {
	SEK ("Swedish krona", "kr", false),
	EUR ("Euro", "€", true),
	USD ("United States dollar", "$", true);
	
	private final String name;
	private final String sign;
	private final boolean signPrefixed;
	
	private Currency(String name, String sign, boolean signPrefixed) {
		this.name = name;
		this.sign = sign;
		this.signPrefixed = signPrefixed;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSign() {
		return sign;
	}
	
	public boolean isSignPrefixed() {
		return signPrefixed;
	}
}
