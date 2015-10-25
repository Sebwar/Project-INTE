package org.gradle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CouponTest.class,
	CustomerTest.class,
	DiscountTest.class,
	MoneyTest.class,
	OrderLineTest.class,
	ReceiptTest.class
})
public class TestSuite {

}
