package com.czs.practice.springlearn.tools;

import com.czs.practice.springlearn.po.TestOne;
import com.czs.practice.springlearn.po.TestTwo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CopySpecialUtil {



    public static void main(String[] args) {


        TestOne one = new TestOne();
        one.setCityId("1");
        one.setDriverId("2");
        one.setTotalPrice("3");
        one.setUserName("4");
        one.setVipLineId("5");
        one.setDriverName("this is one");

        TestTwo two = new TestTwo();
        two.setCity_id("6");
        two.setDriver_id("7");
        two.setTotal_price("8");
        two.setUser_name("9");
        two.setVip_line_id("10");
        two.setDriverName("this is two");

        try {
            System.out.println(two);
            System.out.println("-------------------------------");
            copyCamel2Underline(one,two);
            System.out.println(two);
//            System.out.println(copyCamel2Underline(one,TestTwo.class));

//            System.out.println(one);
//            System.out.println("-------------------------------");
//            copyUnderline2Camel(two,one);
//            System.out.println(one);
//            System.out.println(copyUnderline2Camel(two,TestOne.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 特殊规则下：复制源对象和目标对象的属性值
     * 将 驼峰法 的属性值，复制为下划线 类型的属性值
     * 如 totalPrice 复制给 total_price
     *    cityName 复制给 city_name
     *    userid 复制给 userid
     *    userId 复制给 user_id
     */
    public static void copyCamel2Underline(Object source, Object target) throws Exception{

        Class sourceClass = source.getClass();//得到对象的Class
        Class targetClass = target.getClass();//得到对象的Class

        Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性
        Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性

        for(Field sourceField : sourceFields){
            String name = sourceField.getName();//属性名
            //转换属性名从 驼峰到下划线
            String nameUnderline = ConvertCamel.underline(new StringBuffer(sourceField.getName())).toString();
            Class type = sourceField.getType();//属性类型

            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);

            Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法

            Object value = getMethod.invoke(source);//执行源对象的get方法得到属性值

            for(Field targetField : targetFields){
                String targetName = targetField.getName();//目标对象的属性名
                //第二个判断用于兼容历史版本中出现的驼峰和下划线命名混合的情况
                if(targetName.equals(nameUnderline) || targetName.equals(name) ){
                    methodName = targetName.substring(0, 1).toUpperCase() + targetName.substring(1);
                    Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法
                    setMethod.invoke(target, value);//执行目标对象的set方法
                }
            }
        }
    }


    public static <T> T copyCamel2Underline(Object source, Class<T> targetClass) throws Exception{
        T t = targetClass.newInstance();
        copyCamel2Underline(source,t);
        return t;
    }



    /**
     * 特殊规则下：复制源对象和目标对象的属性值
     * 将 下划线 的属性值，复制为驼峰法 类型的属性值
     * 如 total_price 复制给 totalPrice
     *    city_name 复制给 cityName
     *    userid 复制给 userid
     *    user_id 复制给 userId
     */
    public static void copyUnderline2Camel(Object source, Object target) throws Exception{

        Class sourceClass = source.getClass();//得到对象的Class
        Class targetClass = target.getClass();//得到对象的Class

        Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性
        Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性

        for(Field sourceField : sourceFields){
            String name = sourceField.getName();//属性名
            //转换属性名从 下划线到驼峰
            String nameCamel = ConvertCamel.camel(new StringBuffer(sourceField.getName())).toString();
            Class type = sourceField.getType();//属性类型

            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);

            Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法

            Object value = getMethod.invoke(source);//执行源对象的get方法得到属性值

            for(Field targetField : targetFields){
                String targetName = targetField.getName();//目标对象的属性名
                //第二个判断用于兼容历史版本中出现的驼峰和下划线命名混合的情况
                if(targetName.equals(name) || targetName.equals(nameCamel)){
                    methodName = targetName.substring(0, 1).toUpperCase() + targetName.substring(1);
                    Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法
                    setMethod.invoke(target, value);//执行目标对象的set方法
                }
            }
        }
    }

    public static <T> T copyUnderline2Camel(Object source, Class<T> targetClass) throws Exception{
        T t = targetClass.newInstance();
        copyUnderline2Camel(source,t);
        return t;
    }


}
