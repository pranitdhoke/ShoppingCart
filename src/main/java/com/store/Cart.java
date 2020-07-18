package com.store;

import java.math.BigDecimal;

public class Cart {
    private BigDecimal amount;

    private Cart(BigDecimal amount){
        this.amount = amount;
    }

    public static Cart getCart(BigDecimal amount){
        return new Cart(amount);
    }

    public BigDecimal generateInvoice(DiscountManager discountManager){
        BigDecimal discount = discountManager.generateDiscountValue(this.amount);

        return amount.subtract(discount);
    }
}
