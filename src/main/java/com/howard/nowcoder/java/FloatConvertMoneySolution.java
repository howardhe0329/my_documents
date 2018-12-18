package com.howard.nowcoder.java;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 将浮点数转换成人民币读法字符串
 *
 * @author howard he
 * @create 2018-12-10 15:42
 */
public class FloatConvertMoneySolution {

    private Map<String, String> numberMap;
    private Map<String, String> map;

    public FloatConvertMoneySolution() {
        numberMap = new HashMap<>();
        numberMap.put("0", "零");
        numberMap.put("1", "壹");
        numberMap.put("2", "贰");
        numberMap.put("3", "叁");
        numberMap.put("4", "肆");
        numberMap.put("5", "伍");
        numberMap.put("6", "陆");
        numberMap.put("7", "柒");
        numberMap.put("8", "捌");
        numberMap.put("9", "玖");
        numberMap.put("10", "拾");
        map = new HashMap<>();
        map.put("3", "佰");
        map.put("4", "仟");
        map.put("5", "万");
        map.put("9", "亿");
    }

    public String convert(double num) {
        String s = String.valueOf(num);
        System.out.println(s);
        System.out.println(s.indexOf("."));
        return "";
    }

    public static void main(String[] args) {
        FloatConvertMoneySolution solution = new FloatConvertMoneySolution();
        double d = 11.111;
        System.out.println(solution.convert(d));
    }
}
