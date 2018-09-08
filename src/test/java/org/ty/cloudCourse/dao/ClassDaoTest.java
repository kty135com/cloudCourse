package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class ClassDaoTest extends BaseTest {

    @Autowired
    private ClassDao classDao;

    @Test
    public void testInsertClass() {
        Class clazz = new Class();
        clazz.setClassName("软件二班");
        clazz.setSchool(new PersonInfo(5));
        int i = classDao.insertOne(clazz);
        System.out.println(i);
    }

    @Test
    public void testUpdateClass() {
        Class c = new Class();
        c.setClassId(3);
        c.setClassName("软件2班");
        System.out.println(c);
        int i = classDao.updateOne(c);
        System.out.println(i);
    }

    @Test
    public void testDeleteClass() {
        int i = classDao.deleteOne(new Class(2));
        System.out.println(i);
    }

    @Test
    public void testGetClassBySchool() {
        List<Class> classList = classDao.getClassBySchool(new PersonInfo(5));
        System.out.println(classList);
    }

    @Test
    public void testGetClassById() {
        Class c = classDao.getOneById(new Class(1));
        System.out.println(c);
    }

    @Test
    public void testQueryTeacherByClass() {
        List<PersonInfo> teacherList = classDao.queryTeacherByClass(classDao.getOneById(new Class(1)));
        teacherList.forEach(System.out::println);
    }

    @Test
    public void testgetClassById() {
        Class c = classDao.getOneById(new Class(11));
        System.out.println(c);
    }

    @Test
    public void testGetCla(){
        Class s = new Class();
        s.setClassName("软件%");
        classDao.getCla(s).forEach(System.out::println);
    }

}
