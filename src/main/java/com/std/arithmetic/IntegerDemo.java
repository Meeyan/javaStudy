package com.std.arithmetic;

/**
 * 整数的相关算法
 *
 * @author zhaojy
 * @date 2017-12-21
 */
public class IntegerDemo {

    /**
     * 解读：java.lang.Integer#numberOfLeadingZeros(int)，此处粘出来便于注解
     *
     * @param i
     * @return int
     */
    public static int numberOfLeadingZeros(int i) {
        if (i == 0)
            return 32;
        int n = 1;
        /*
         * 二分查找的思想
         *   1. 若右移16位为0，那么i的前16为都为0（接下来需要搞定，低16位中的前导0个数），所以将i左移16位，低16位变为高16位。
         */
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;   // 左移并赋值，左移后，后面的16位会自动补0
        }

        /*
         *  2.  若i右移24位为0，那么i的前8位都为0，所以左移8位，解决后面的24位中的前几位为0。
         */
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8; // 左移并赋值，左移后，后面的8位会自动补0
        }

       /*
        * 3. 右移28位后i为0，则i的前4位都为0.
        */
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }

        /*
         * 4. 如果右移30位后i为0，那么i的前2位为0。
         */
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }

        /*
         * 第1位为符号位（0：正数，1：负数），负数的获得规则：将负数的绝对值二进制取反（32位）再加1，由此得得结果，其高位都是1，没有0。
         * 所以负数的前导0的个数为0个。
         * 如果i的第1位=0，第2位不为0，右移31位后，i为0，此时n=1；
         */
        n -= i >>> 31;
        return n;
    }
}
