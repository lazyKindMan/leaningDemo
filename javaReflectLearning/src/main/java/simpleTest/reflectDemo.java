package simpleTest;

import base.LoadClassUtils;
import entity.Apple;
import entity.MemberValues;
import org.junit.Test;
import utils.StrUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class reflectDemo {
    @Test
    public void normal(){
        Apple apple = new Apple();
        apple.setPrice(4);
        System.out.println(apple.getPrice());
    }
    @Test
    public void reflect(){
        try {
            Class clz = Class.forName("entity.Apple");
            Method setMethod = clz.getMethod("setPrice", Integer.class);
            Method getMethod = clz.getMethod("getPrice");
            Constructor constructor = clz.getConstructor();
            Object obj = constructor.newInstance();
            setMethod.invoke(obj,4);
            System.out.println((Integer) getMethod.invoke(obj));
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test1(){
        Apple apple = new Apple();
        try {
            MemberValues memberValues = LoadClassUtils.getAllMember(apple.getClass());
            System.out.println(memberValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        System.out.println(StrUtils.equals("applethis$12","this\\$[0-9]+"));
    }

}
