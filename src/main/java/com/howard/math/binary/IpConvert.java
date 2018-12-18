package com.howard.math.binary;

/**
 * IP转换成32位整数
 *
 * @author howard he
 * @create 2018-12-14 16:12
 */
public class IpConvert {

    public int convert(String ip) {
        String[] seg = ip.split("\\.");
        int result = 0;
        int move = 24;
        for (int i = 0; i < seg.length; i++) {
            int val = Integer.parseInt(seg[i].trim());
            result = (val << move) | result;
            move -= 8;
        }
        return result;
    }

    public String integerConvert(int ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int offset = (ip >> 8 * (3 - i)) & 255;
            sb.append(offset).append(".");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        String ip = "192.168.1.3";
        IpConvert convert = new IpConvert();

        int result = convert.convert(ip);
        System.out.println(result);

        System.out.println(convert.integerConvert(result));
    }
}
