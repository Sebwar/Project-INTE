package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;

public class CouponTest {

	int couponDeduct = 20;
	int couponMinAmount = 200;
	int amount = 500;
	int result;

    //Test method for deducting
    @Test
    public void testDeduct() {
    	CouponTest ct = new CouponTest();
		result = ct.amount - couponDeduct;
        assertTrue(result == 480);
    }
    
    //Test method for deducting if more than couponMinAmount
    @Test
    public void testDeductIfMinAmount() {
    	CouponTest ct = new CouponTest();
    	if(amount < couponMinAmount) {
		result = ct.amount - couponDeduct;
        assertTrue(result == 480);
    	}
    }

} 
