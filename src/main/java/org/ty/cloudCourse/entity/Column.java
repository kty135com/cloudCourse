package org.ty.cloudCourse.entity;

/**
 * Created by kangtaiyang on 2018/5/11.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 栏目
 * 栏目id
 * 栏目名
 * 栏目描述
 * 权重
 * 审核人员
 * 父栏目
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column extends BaseEntity {
    private Integer id;
    private Integer columnId;
    private String columnName;
    private String columnDesc;
    private Integer priority;
    private PersonInfo personInfo;
    private Column parentColumn;

    public Column(Integer id) {
        this.columnId = this.id = id;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = this.id = columnId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("personInfo",personInfo);
        map.put("parentColumn",parentColumn);
        return map;
    }
}
