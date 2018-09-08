package org.ty.cloudCourse.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/26
 */
public class SetGetTracker {
    public static void trackSetGet(List<Integer> setGets, Class<?> cl){
        System.out.println(cl.getName());
        for (Field f :
                cl.getDeclaredFields()) {
            System.out.println(Arrays.asList(f.getAnnotations()));
            SetGet sg = f.getAnnotation(SetGet.class);
            if (sg!= null){
                System.out.println("this is setget of "+f.getName()+" , and desc is "+sg.desc());
            }
        }
    }
}
