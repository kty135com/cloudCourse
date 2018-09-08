package org.ty.cloudCourse.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.impl.CrudServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class PersonDaoTest extends BaseTest {
    @Autowired
    private PersonDao personDao;

    @Test
    public void testQuerySchool() {
        List<PersonInfo> personList = personDao.querySchool();
        System.out.println(personList);
    }

    @Test
    public void testLogin() {
        PersonInfo person = new PersonInfo();
        person.setUserName("stu1");
        person.setPassword("123");
        person = personDao.login(person);
        System.out.println(person);

    }

    @Test
    @Ignore
    public void testGetPersonById() {
        System.out.println(personDao.getOneById(new PersonInfo(1)));
    }

    @Test
    @Ignore
    public void testInsertPerson() {
        PersonInfo person = new PersonInfo();
        person.setUserName("abcd");
        person.setPassword("abc");
        person.setUserType(0);
        person.setEnableStatus(0);
        System.out.println(personDao.insertOne(person));
        System.out.println(person.getUserId());
    }

    @Test
    public void testDeletePerson() {
        System.out.println(personDao.deleteOne(new PersonInfo(16)));
    }

    @Test
    public void testupdatePerson() {
        PersonInfo person = personDao.getOneById(new PersonInfo(1));
        person.setPassword("123");
        System.out.println(person);
        System.out.println(personDao.updateOne(person));
    }

    @Test
    public void testQueryTeacherBySchool() {
        PersonInfo school = new PersonInfo(5);
        PersonInfo teacher = new PersonInfo();
        teacher.setParentInfo(school);
        List<PersonInfo> teacherList = personDao.queryTeacherBySchool(school);
        System.out.println(teacherList);
    }
}
