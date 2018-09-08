package org.ty.cloudCourse.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangtaiyang
 * @date 2018/7/3
 */
public abstract class BaseEntity {
    protected Integer id = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String,BaseEntity> getParentEntity(){
        return new HashMap<>();
    }
}
