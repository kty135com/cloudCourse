package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.dao.GiftDao;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Gift;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.GiftService;

import static org.junit.Assert.*;

/**
 * @author kangtaiyang
 * @date 2018/7/7
 */
public class GiftServiceImplTest extends BaseTest {

    @Autowired
    private GiftService giftService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private GiftDao giftDao;

    @Test
    public void testGetoneById() {
        System.out.println(crudService.getOneById(new Gift(5),giftDao));
    }

    @Test
    public void testQueryGiftByClass() {
        giftService.queryGiftByClass(new Class(1)).getAList().forEach(System.out::println);
    }
}