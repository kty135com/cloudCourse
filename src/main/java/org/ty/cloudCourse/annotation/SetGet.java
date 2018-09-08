package org.ty.cloudCourse.annotation;

import java.lang.annotation.*;

/**
 * @author kangtaiyang
 * @date 2018/6/26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetGet {

    public int id() default -1;
    public String desc() default "there is no desc";
}
