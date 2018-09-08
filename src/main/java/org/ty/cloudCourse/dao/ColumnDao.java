package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Column;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/9.
 */
public interface ColumnDao extends BaseDao {

    int insertOne(Column column);

    Column getOneById(Column column);

    int updateOne(Column column);

    int deleteOne(Column column);

    List<Column> queryParentColumnBySchool(PersonInfo school);

    List<Column> getColumnByParentId(Column column);

    List<Column> queryAllColumnBySchool(PersonInfo school);
}
