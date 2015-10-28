package org.gradle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CategoryTest.class,
	CouponTest.class,
	DiscountTest.class,
	Ekvivalensklassuppdelning.class,
	MoneyTest.class,
	OrderLineTest.class,
	ReceiptTest.class,
	stateTest.class
})
public class TestSuite {

}
