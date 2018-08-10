package com.newsee.common.utils;

/**
 * @author mu.jie
 * @Date 2018/7/17 10:58
 */
public class MoneyToCNFormat {
    private static final String[] CN_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_MONETETARY_UNIT = {"分", "角", "元", "拾", "佰", "仟",
            "万", "拾", "佰", "仟", "亿", "拾",
            "佰", "仟", "兆", "拾", "佰", "仟"};
    private static final String CN_FULL = "整";
    private static final String CN_NEGATIVE = "负";
    private static final String CN_ZERO = "零角零分";
    private static final long MAX_NUMBER = 10000000000000000L;//最大16位整数
    private static final String INVALID_AMOUNT = "金额超出最大金额千兆亿(16位整数)";

    public static String formatToCN(double amount) {
        if (Math.abs(amount) >= MAX_NUMBER) {
            return INVALID_AMOUNT;
        }
        StringBuilder result = new StringBuilder();
        long value = Math.abs(Math.round(amount * 100));
        int i = 0;
        while (true) {
            int temp = (int) (value % 10);
            result.insert(0, CN_MONETETARY_UNIT[i]);
            result.insert(0, CN_NUMBERS[temp]);
            value = value / 10;
            if (value < 1)
                break;
            i++;
        }
        //"零角零分" 转换成 "整"
        int index = result.indexOf(CN_ZERO);
        if (index > -1) {
            result.replace(index, index + 4, CN_FULL);
        }
        //负数
        if (amount < 0) {
            result.insert(0, CN_NEGATIVE);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        double amountTest = 987654321654321.12D;
        System.out.println("金额[" + amountTest + "]=" + formatToCN(amountTest));
    }
}
