package edu.nju.service;

/**
 * Created by Dora on 2017/3/10.
 */
public interface DiscountLevel {

    double convert(double original);

    static DiscountLevel decide(int credit) {

        if(credit >= 20000) {
            return CreditDiscount.PREMIUM;

        } else if(credit >= 10000) {
            return CreditDiscount.MIDIUM;

        } else if(credit >= 6000) {
            return CreditDiscount.PRIMARY;

        } else {
            return CreditDiscount.NONDISCOUNT;
        }

    }
}
