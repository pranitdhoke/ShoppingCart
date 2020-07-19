package com.store.service;

import com.store.error.NegativeAmountException;

import java.math.BigDecimal;

public class RegularCustomer implements DiscountManager {
    @Override
    public BigDecimal generateDiscountValue(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        if (amount.compareTo(BigDecimal.valueOf(5000)) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal discount = BigDecimal.ZERO;
        if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
            BigDecimal calculatedDiscount = getDiscountFor5kTo10KSlab(amount);
            discount = discount.add(calculatedDiscount);
        }
        if (amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            BigDecimal calculatedDiscount = getDiscountForAbove10KSlab(amount);
            discount = discount.add(calculatedDiscount);
        }
        return discount;
    }

    private BigDecimal getDiscountForAbove10KSlab(BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(10000));

        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(20)
                .divide(BigDecimal.valueOf(100)));
    }


    private BigDecimal getDiscountFor5kTo10KSlab(final BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount;
        if (amount.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            eligibleAmountForDiscount = BigDecimal.valueOf(5000);
        } else {
            eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(5000));
        }

        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(10)
                .divide(BigDecimal.valueOf(100)));
    }

}
