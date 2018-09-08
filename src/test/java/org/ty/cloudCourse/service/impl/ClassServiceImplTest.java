package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.annotation.SetGetTracker;
import org.ty.cloudCourse.dao.ClassDao;
import org.ty.cloudCourse.dao.PersonDao;
import org.ty.cloudCourse.dao.StudentDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.ClassService;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;
import org.ty.cloudCourse.util.Tuple;
import org.ty.cloudCourse.util.TyArrayList;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * Created by kangtaiyang on 2018/6/20.
 */
public class ClassServiceImplTest extends BaseTest {
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private CrudService crudService;

    @Test
    public void testQueryStudentByClass() {
        Class c = classDao.getOneById(new Class(1));
        AllExecution execution = classService.queryStudentByClass(c);
        execution.getAList().forEach(System.out::println);
    }

    @Test
    public void testQueryTeacherByClass() {
        Class c = classDao.getOneById(new Class(1));
        AllExecution execution = classService.queryTeacherByClass(c);
        execution.getAList().forEach(System.out::println);
    }

    @Test
    public void testQueryCourseByClass() {
        Class c = classDao.getOneById(new Class(1));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        SetGetTracker.trackSetGet(list, c.getClass());
    }

    @Test
    public void testGetClassById() {
        Class c = (Class) crudService.getOneById(new Class(23), classDao).getA();
        System.out.println("最后查除来Class = " + c);
    }

    @Test
    public void testInsertClass() {
        Class c = new Class();
        c.setClassName("会计3");
        c.setStudentCount(5);
        crudService.insertOne(c, classDao);
    }

    @Test
    public void testUpdateClass() {
        Class c = (Class) crudService.getOneById(new Class(11), classDao).getA();
        c.setClassName("唐不理波");
        crudService.updateOne(c, classDao);
    }

    @Test
    public void testDeleteClass() {
//        crudService.deleteOne(new Class(21), classDao);
        List tylist = new TyArrayList(1);
        tylist.add("a");
        tylist.add("b");
        tylist.add("c");
        System.out.println(tylist.get(1));
        tylist.remove("b");
        System.out.println(tylist.get(2));
    }
}
