package com.lts;

import org.springframework.core.convert.TypeDescriptor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author luotianshun
 * @date 2021/5/24
 * @menu
 */
public class TypeDescriptorTest<V> {
    public V v;

    public String a;

    public static void main(String[] args) throws NoSuchFieldException, IntrospectionException {
//        Field v1 = ReflectionUtils.findField(TypeDescriptorTest.class, "v");
        Field v = TypeDescriptorTest.class.getField("v");
        //test1,主要是看下构造函数TypeDescriptor(v)中对于typeVariable的field.getType()返回什么--》Object。~泛型擦除。
        TypeDescriptor typeDescriptor = new TypeDescriptor(v);

//        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("v", TypeDescriptorTest.class);
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("v", TypeDescriptorTest.class, null, null);

        TypeDescriptor typeDescriptor1 = new TypeDescriptor(TypeDescriptorTest.class.getField("v"));
        TypeDescriptor narrow = typeDescriptor1.narrow(new ArrayList<>());

        TypeDescriptor typeDescriptor2 = new TypeDescriptor(TypeDescriptorTest.class.getField("a"));
        TypeDescriptor narrow2 = typeDescriptor2.narrow(new Object());
        String string = "d";
    }
}
