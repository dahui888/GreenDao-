package com.ali.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mumu on 2018/7/5.
 */

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Person person = new Person();

        //类模板对象的三种获取方式
        //1.通过.class
        Class<Person> personClass = Person.class;
        //2.通过全类名
        Class<Person> aClass = (Class<Person>) Class.forName("com.ali.reflectdemo.Person");
        //3.通过对象
        Class<Person> aClass1 = (Class<Person>) person.getClass();

        //获取public声明的成员变量
//        Field[] fields = aClass1.getFields();
        //所有声明的成员变量
        Field[] fields1 = aClass1.getDeclaredFields();
        //根据属性名字获取该属性对象
        Field age = aClass1.getDeclaredField("age");
        //设置可访问
        age.setAccessible(true);
        //通过反射给属性赋值
        age.setInt(person, 18);

        //根据属性名字获取该属性对象
        Field name = aClass1.getDeclaredField("name");





        name.setAccessible(true);
        //通过反射给属性赋值
        name.set(person, "wang");

        //指定方法名字，和方法参数类型，去获取Method的对象
        Method setAge = aClass1.getDeclaredMethod("setAge", int.class);
        //通过反射调用方法
        setAge.setAccessible(true);
        setAge.invoke(person, 28);
//        System.out.println("=========" + person);

        //通过类模板对象获取构造器，然后通过构造器创建对象
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        Person zhang = declaredConstructor.newInstance("zhang", 3);
        System.out.println("=========" + zhang);

        //如果有无参构造器可以用类模板对象newInstance的方式构造对象
        Person person1 = personClass.newInstance();
        System.out.println("=========" + person1);

    }

}
