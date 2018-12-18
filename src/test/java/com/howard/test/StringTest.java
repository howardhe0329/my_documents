package com.howard.test;

/**
 * @author howard he
 * @create 2018/11/2 11:11
 */
public class StringTest {

    public static int hash(Object key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (16 - 1);
    }

    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.hashCode());
        String b = "b";
        System.out.println(b.hashCode());
        String a1 = "aa";
        System.out.println(a1.hashCode());
        String a2 = "";
        System.out.println(a2.hashCode());
        System.out.println(" ".hashCode());
        System.out.println("  ".hashCode());
        System.out.println("---------------------------------");
        char[] a1Chars = a1.toCharArray();
        int h = 0;
        // hash
        for (int i = 0; i < a1.length(); i++) {
            h = 31 * h + a1Chars[i];
        }
        System.out.println(h);
        System.out.println("-----------------------------------");
        Integer i = 1;
        Integer i2 = 2;
        System.out.println(i.hashCode());
        System.out.println(i2.hashCode());

        System.out.println("------------------------------------");
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "a", "a".hashCode(), StringTest.hash("a"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "b", "b".hashCode(), StringTest.hash("b"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "c", "c".hashCode(), StringTest.hash("c"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "A", "A".hashCode(), StringTest.hash("A"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "B", "B".hashCode(), StringTest.hash("B"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "C", "C".hashCode(), StringTest.hash("C"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "1", "1".hashCode(), StringTest.hash("1"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "2", "2".hashCode(), StringTest.hash("2"));
        System.out.printf("key: %s; hashCode: %d; hash: %d\n", "3", "3".hashCode(), StringTest.hash("3"));
        System.out.println("a".hashCode() ^ ("a".hashCode() >>> 16));
    }


}
