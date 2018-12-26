package com.czs.practice.springlearn.tools;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lantian
 * @create 2017-4-20.
 * @description 不同类相同属性名值拷贝
 */
public class CopyUtil {


    private CopyUtil() { throw new IllegalStateException("Utility class"); }

    private static final Logger logger = LoggerFactory.getLogger(CopyUtil.class);

    /**
     * 复制源对象和目标对象的属性值
     *
     */
    public static void copy(Object source, Object target) throws Exception{

        Class sourceClass = source.getClass();//得到对象的Class
        Class targetClass = target.getClass();//得到对象的Class

        Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性
        Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性

        for(Field sourceField : sourceFields){
            String name = sourceField.getName();//属性名
            Class type = sourceField.getType();//属性类型

            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);

            Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法

            Object value = getMethod.invoke(source);//执行源对象的get方法得到属性值

            for(Field targetField : targetFields){
                String targetName = targetField.getName();//目标对象的属性名

                if(targetName.equals(name)){
                    Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法

                    setMethod.invoke(target, value);//执行目标对象的set方法
                }
            }
        }
    }

    /**
     * 复制List内部对象属性
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List sourceList,Class<T> targetClass){
        List<T> result = Lists.newArrayList();

        if(CollectionUtils.isEmpty(sourceList)) {
            return result;
        }
        sourceList.stream().forEach(source->{
            try {
                //T有无参构造方法
                T t = targetClass.newInstance();
                BeanUtils.copyProperties(source,t);
                result.add(t);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        });
        return result;
    }

    /**
     * 复制类属性 内部处理异常情况
     * 调用方需判断返回null值情况
     * @param source
     * @param targetClass 需要无参构造方法
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T copyProperties(Object source,Class<T> targetClass){
        T t;
        try {
            //无参构造方法
            t = targetClass.newInstance();
            if(null !=source) {
                BeanUtils.copyProperties(source, t);
            }
            return t;
        } catch (InstantiationException | IllegalAccessException | BeansException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

}
