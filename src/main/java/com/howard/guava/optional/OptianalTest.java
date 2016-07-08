package com.howard.guava.optional;

import com.google.common.base.Optional;

/**
 * Created by howard on 16/7/6.
 */
public class OptianalTest {

    public static void main(String[] args) {
        //创建引用缺失的Optional实例
        Optional optional = Optional.absent();
        try {
            optional.get(); // 获取引用,如果引用缺失则抛IllegalStateException异常
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

        //创建指定引用的Optional实例,如果引用为null则快速失败
        try {
            Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        //创建指定引用的Optional实例，若引用为null则表示缺失
        Optional optional1 = Optional.fromNullable(null);
        try {
            optional1.get(); // 获取引用,如果引用缺失则抛IllegalStateException异常
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

        //如果Optional包含非null的引用,则返回true, 否则false.
        optional.isPresent();

        //返回Optional包含的引用,如果为缺失引用则返回指定的值
        optional.or(new User("default", 0));

        //返回Optional所包含的引用，若引用缺失，返回null
        optional.orNull();

        //返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
        optional.asSet();

    }
}
