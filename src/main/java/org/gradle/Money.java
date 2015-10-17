package org.gradle;

public class Money implements Comparable<Money> {
	private static final int MAJOR_UNIT = 100;
	
	private Currency currency;
	private long amount;  //Stored in minor units, e.g. cents or öre.
	
	public Money(Currency currency, long amount) {
		if(currency == null)
			throw new IllegalArgumentException("Currency cannot be null.");
		if(amount < 0)
			throw new IllegalArgumentException("Amount cannot be negative.");
		this.currency = currency;
		this.amount = amount;
	}
	
	public Money(Currency currency, long amountInMajorUnit, long amountInMinorUnit) {
		this(currency, amountInMajorUnit * MAJOR_UNIT + amountInMinorUnit);
	}
	
	public long getAmountOfMajorUnit() {
		return amount / MAJOR_UNIT;
	}
	
	public long getAmountOfMinorUnit() {
		return amount % MAJOR_UNIT;
	}
	
	public long getTotalAmountInMinorUnit() {
		return amount;
	}
	
	public Money add(long addend) {
		if(addend < 0)
			throw new IllegalArgumentException("Cannot add a negative amount.");
		return new Money(currency, amount + addend);
	}
	
	public Money add(Money addend) {
		if(addend.currency != currency)
			throw new IllegalArgumentException("The money has to be of the same currency");
		return add(addend.amount);
	}
	
	public Money subtract(long addend) {
		if(addend < 0)
			throw new IllegalArgumentException("Cannot add a negative amount.");
		return new Money(currency, amount + addend);
	}
	
	public Money subtract(Money addend) {
		if(addend.currency != currency)
			throw new IllegalArgumentException("The money has to be of the same currency");
		return subtract(addend.amount);
	}
	
	public int compareTo(Money other) {
		if(other == null)
			throw new IllegalArgumentException("Money cannot be null.");
		if(other.currency != currency)
			throw new IllegalArgumentException("The money has to be of the same currency.");
		return (int)(amount - other.amount);
	}
	
	public String toString() {
		return null;
	}
}
