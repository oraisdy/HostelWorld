package edu.nju.service;

/**
 * Created by Dora on 2017/3/10.
 */
public interface DiscountLevel {

    double convert(double original);

    static DiscountLevel decide(int credit) {

        if(credit >= 10000) {
            return CreditDiscount.PREMIUM;

        } else if(credit >= 5000) {
            return CreditDiscount.MIDIUM;

        } else if(credit >= 2000) {
            return CreditDiscount.PRIMARY;

        } else {
            return CreditDiscount.NONDISCOUNT;
        }

    }
}
