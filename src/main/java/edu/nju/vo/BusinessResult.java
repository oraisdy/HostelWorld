package edu.nju.vo;

/**
 * Created by Dora on 2017/3/10.
 */
public enum BusinessResult implements Business {

    PASS {
        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public boolean getAsBoolean() {
            return true;
        }

        @Override
        public String toString() {
            return "操作成功";
        }
    },
    ERROR {
        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public boolean getAsBoolean() {
            return false;
        }
    },
    INSUFFICIENT_FUNDS {
        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public boolean getAsBoolean() {
            return false;
        }

        @Override
        public String toString() {
            return "余额不足";
        }
    },
    INACTIVE_ACCOUNT {
        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public boolean getAsBoolean() {
            return false;
        }

        @Override
        public String toString() {
            return "账户未激活";
        }
    },
    SUSPENDED_ACCOUNT {
        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public boolean getAsBoolean() {
            return false;
        }

        @Override
        public String toString() {
            return "账户已停用";
        }
    }


}
