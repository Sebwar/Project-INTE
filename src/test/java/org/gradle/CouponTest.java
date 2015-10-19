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
    
    //Test if amount is bigger or equal than couponMinAmount
    @Test
    public void testAmountIsBiggerThanCouponMinAmount() {
    	CouponTest ct = new CouponTest();
        assertTrue(ct.amount >= couponMinAmount);
    	}
    }
 