package edu.nju.service;

/**
 * Created by Dora on 2017/3/10.
 */
public enum CreditDiscount implements DiscountLevel {

    PRIMARY {
        @Override
        public double convert(double original) {
            return original * 0.9;
        }
        @Override
        public String toString() {
            return "初级会员(10% discount)";
        }
    },
    MIDIUM {
        @Override
        public double convert(double original) {
            return original * 0.85;
        }
        @Override
        public String toString() {
            return "中级会员(15% discount)";
        }
    },
    PREMIUM {
        @Override
        public double convert(double original) {
            return original * 0.8;
        }
        @Override
        public String toString() {
            return "高级会员(20% discount)";
        }
    },
    NONDISCOUNT {
        @Override
        public double convert(double original) {
            return original;
        }
        @Override
        public String toString() {
            return "无优惠";
        }
    };

}
