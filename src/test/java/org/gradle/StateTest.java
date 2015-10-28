package org.gradle;

import static org.junit.Assert.*;

import org.gradle.discounts.Coupon;
import org.junit.Test;
public class StateTest {

	/*	Testfall 1
	 * 	1. L�gg in ny vara
	 * 	2. L�gg in ny vara
	 * 	3. L�gg in vara med samma produktID
	 * 	4. Ge medlemskort (L�gger in customer i kvitto-klassen)
	 * 	5. L�gg in giltig kupong f�r detta k�p
	 * 	6. Betala
	 */
	@Test
	public void testCaseOne() {
		//Product(id, name, price, weightPriced)
		Product product1 = new Product(0, "Product1", new Money(Currency.SEK, 1000), false); //10kr
		Product product2 = new Product(1, "Product2", new Money(Currency.SEK, 5000), false); //50kr
		
		//L�gger in produkter i orderLines f�r att kunna hantera dem i kvitto-klassen
		//OrderLine(product, quantity)
		OrderLine orderLine1 = new OrderLine(product1, 1);
		OrderLine orderLine2 = new OrderLine(product2, 2);
		
		//Customer(orgNr, firstName, lastName, street, address, phone)
		Customer customer = new Customer("123", "Name", "LastName", "Street", "Adress", "12345678");
		
		//Receipt(customer) ; Customer l�ggs in i kvitto-klassen
		Receipt receipt = new Receipt(customer);
		
		//Coupon(amount, deduction, requiredAmount)
		Coupon coupon = new Coupon(new Money(Currency.SEK, 11000), new Money(Currency.SEK, 1000), new Money(Currency.SEK, 10000));
		
		//L�gger in orderLine i kvitto-klassen
		receipt.addOrderLine(orderLine1);
		receipt.addOrderLine(orderLine2);
		
		//L�gger in kuponen
		receipt.addCoupon(coupon);
		
		//Betalning
		//System.out.println( receipt.toString() ); //Skriver ut kvitto
		String expectedString = "New sale for: Name LastName\n"
				+ "Product1  1st*10.0 kr  10.0 kr\n"
				+ "Product2  2st*50.0 kr  100.0 kr\n"
				+ "Reduction from coupons: 10.0 kr\n"
				+ "Total price after reduction: 100.0 kr";
		//P�grund av att detta system inte �r helt klart blir inte resultatet korrekt �nnu
		assertEquals(expectedString, receipt.toString());
	}
	
	/*	Testfall 2
	 * 	1. L�gg in ny vara med med ogiltigt ID
	 * 	2. L�gg in ny vara
	 * 	3. Ingen kupong
	 * 	4. Inget medlemskort (L�gger in customer i kvitto-klassen)
	 * 	5. Betala
	 */
	@Test
	public void testCaseTwo() {
		//Product(id, name, price, weightPriced)
		try {
			Product productInvalid = new Product(-1, "Product1", new Money(Currency.SEK, 10000), false); //100kr
		} catch (IllegalArgumentException e){};
		Product product = new Product(0, "Product1", new Money(Currency.SEK, 10000), false); //100kr
		
		//L�gger in produkter i orderLines f�r att kunna hantera dem i kvitto-klassen
		//OrderLine(product, quantity)
		OrderLine orderLine = new OrderLine(product, 1);
		
		//Receipt(customer) ; Customer l�ggs in i kvitto-klassen
		Receipt receipt = new Receipt();
		
		//L�gger in orderLine i kvitto-klassen
		receipt.addOrderLine(orderLine);
		
		//Betalning
		//System.out.println( receipt.toString() ); //Skriver ut kvitto
		String expectedString = "New sale:\n"
				+ "Product1  1st*100.0 kr  100.0 kr\n"
				+ "Total price: 100.0 kr";
		//P�grund av att detta system inte �r helt klart blir inte resultatet korrekt �nnu
		assertEquals(expectedString, receipt.toString());
	}
}
