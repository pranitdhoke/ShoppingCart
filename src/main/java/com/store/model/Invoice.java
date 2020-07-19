package com.store.model;

import com.store.service.DiscountManager;
import com.store.service.PremiumCustomer;
import com.store.service.RegularCustomer;

import java.math.BigDecimal;

/**
 * This is a model class for Invoice.
 * @author Pranit
 * @since 19th July 2020
 */
public class Invoice {

    private final Cart cart;
    private final CustomerType customerType;


    public Invoice(Cart cart, CustomerType customerType) {
        this.cart = cart;
        this.customerType = customerType;
    }

    public static Invoice getInstance(Cart cart, CustomerType customerType) {
        return new Invoice(cart, customerType);
    }

    public BigDecimal calculateTotal() {
        DiscountManager discountManager = getDiscountManager(this.customerType);
        if(null == discountManager){
            return cart.getAmount();
        }else{
            BigDecimal discount = discountManager.generateDiscountValue(cart.getAmount());
            return cart.getAmount().subtract(discount);
        }
    }

    private DiscountManager getDiscountManager(CustomerType customerType) {
        DiscountManager discountManager;
        switch (customerType) {
            case REGULAR:
                discountManager = new RegularCustomer();
                break;
            case PREMIUM:
                discountManager = new PremiumCustomer();
                break;
            default:
                discountManager = null;
                break;
        }
        return discountManager;
    }
}

