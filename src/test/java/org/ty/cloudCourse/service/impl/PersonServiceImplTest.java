package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.dao.PersonDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.PersonService;
import org.ty.cloudCourse.service.impl.CrudServiceImpl;
import org.ty.cloudCourse.service.impl.PersonServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by kangtaiyang on 2018/5/17.
 */
public class PersonServiceImplTest extends BaseTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private CrudService crudService;

    @Autowired
    private PersonDao personDao;

    @Test
    public void testGetPersonById() {
        AllExecution allExecution = crudService.getOneById(new PersonInfo(15), personDao);
        System.out.println(allExecution.getStateInfo());
        System.out.println(allExecution.getA());
    }

    @Test
    public void testLogin() {
        PersonInfo token = new PersonInfo();
        token.setUserName("tea1");
        token.setPassword("1234");
        AllExecution execution = personService.login(token);
        System.out.println(execution.getStateInfo());
    }

    @Test
    public void testQuerySchool() {
        AllExecution execution = personService.querySchool();
        System.out.println(execution.getStateInfo());
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryTeacherBySchool() {
        AllExecution execution = personService.queryTeacherBySchool(new PersonInfo(5));
        System.out.println(execution.getStateInfo());
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryClassBySchool() {
        AllExecution execution = personService.queryClassBySchool(new PersonInfo(5));
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryCategoryBySchool() {
        AllExecution execution = personService.queryCategoryBySchool(new PersonInfo(5));
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryClassByTeacher() {
        AllExecution execution = personService.queryClassByTeacher(new PersonInfo(6));
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryCourseByTeacher() {
        AllExecution execution = personService.queryCourseByTeacher(new PersonInfo(6));
        System.out.println(execution.getAList());
    }

    @Test
    public void testQueryStudentBySchool() {
        AllExecution execution = personService.queryStudentBySchool(new PersonInfo(5));
        System.out.println(execution.getAList());
    }
}
