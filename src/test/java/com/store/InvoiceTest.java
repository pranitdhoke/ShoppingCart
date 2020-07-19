package com.store;

import com.store.error.NegativeAmountException;
import com.store.model.Cart;
import com.store.model.CustomerType;
import com.store.model.Invoice;
import com.store.service.PremiumCustomer;
import com.store.service.RegularCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This is a test class for Invoice.
 *
 * @author Pranit
 * @since 19th July 2020
 */
public class InvoiceTest {

    private Invoice systemUnderTest;

    @Test
    @DisplayName("Calculate total with discount for regular customer when amount is non negative.")
    public void calculateTotal_validDiscount(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(1000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.REGULAR);

        //When
        BigDecimal total = systemUnderTest.calculateTotal();

        //Then
        assertThat(total).isNotNull();
    }

    @Test
    @DisplayName("Calculate total amount for a regular customer when amount is zero.")
    public void calculateTotal_validDiscountForZeroAmount(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(0));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.REGULAR);

        //When
        BigDecimal total = systemUnderTest.calculateTotal();

        //Then
        assertThat(total).isNotNull();
        assertThat(total).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Calculate total amount with discount for a non negative amount below 5000.")
    public void generateBill_regularCustomerBelow5000(){
        // Given
        BigDecimal amount = BigDecimal.valueOf(2500);
        Cart cart = Cart.getInstance(amount);
        systemUnderTest = Invoice.getInstance(cart, CustomerType.REGULAR);

        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount).isEqualTo(amount);
    }

    @Test
    @DisplayName("Calculate total amount with discount if amount is between 5000 - 10000.")
    public void generateBill_regularCustomerFrom5000To10000(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(8000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.REGULAR);

        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(7700));
    }



    @Test
    @DisplayName("Calculate total amount with discount if amount is above 10k.")
    public void generateBill_regularCustomerForAbove10K(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(15000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.REGULAR);

        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(13500));
    }

    @Test
    @DisplayName("Calculate total amount for a premium customer when amount is zero.")
    public void calculateTotal_validDiscountForZeroAmountForPremiumCustomer(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(0));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.PREMIUM);

        //When
        BigDecimal total = systemUnderTest.calculateTotal();

        //Then
        assertThat(total).isNotNull();
        assertThat(total).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Calculate total amount with discount if amount is below 4k for Premium customer.")
    public void generateBill_PremiumCustomerForBelow4K(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(4000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.PREMIUM);

        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(3600));
    }

    @Test
    @DisplayName("Calculate total amount with discount if amount is between 4k to 8k for Premium customer.")
    public void generateBill_PremiumCustomerForBetween4kTo8k(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(8000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.PREMIUM);
        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(7000));
    }

    @Test
    @DisplayName("Calculate total amount with discount if amount is between 8k to 12k for Premium customer.")
    public void generateBill_PremiumCustomerForBetween8kTo12k(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(12000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.PREMIUM);
        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(10200));
    }

    @Test
    @DisplayName("Calculate total amount with discount if amount is above 12k for Premium customer.")
    public void generateBill_PremiumCustomerForAbove12k(){
        // Given
        Cart cart = Cart.getInstance(BigDecimal.valueOf(20000));
        systemUnderTest = Invoice.getInstance(cart, CustomerType.PREMIUM);

        //When
        BigDecimal totalAmount = systemUnderTest.calculateTotal();

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(15800));
    }
}
