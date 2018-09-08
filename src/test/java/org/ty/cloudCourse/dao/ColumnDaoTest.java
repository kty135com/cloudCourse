package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Column;
import org.ty.cloudCourse.entity.PersonInfo;

/**
 * Created by kangtaiyang on 2018/6/11.
 */
public class ColumnDaoTest extends BaseTest {
    @Autowired
    private ColumnDao columnDao;

    @Test
    public void testInsertColumn() {
        Column c = new Column();
        c.setColumnName("逻辑与算法");
        c.setColumnDesc("我又是描述！");
        c.setPriority(30);
        c.setParentColumn(new Column(1));
        c.setPersonInfo(new PersonInfo(5));
        columnDao.insertOne(c);
    }

    @Test
    public void testGetColumnById() {
        System.out.println(columnDao.getOneById(new Column(1)));
    }

    @Test
    public void testUpdateColumn() {
        Column c = new Column(3);
        c.setPriority(44);
        c.setColumnDesc("我是描述啊嗷嗷");
        columnDao.updateOne(c);
    }

    @Test
    public void testDeleteColumn() {
        columnDao.deleteOne(new Column(3));
    }

    @Test
    public void testQueryAllColumnBySchool() {
        System.out.println(columnDao.queryAllColumnBySchool(new PersonInfo(5)));
    }
}
