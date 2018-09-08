package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Gift;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class GiftDaoTest extends BaseTest {

    @Autowired
    private GiftDao giftDao;

    @Test
    public void testInsertGift() {
        Gift gift = new Gift();
        gift.setClazz(new Class(1));
        gift.setCreateTime(sdf.format(new Date()));
        gift.setGiftName("小猫");
        gift.setGiftDesc("我是来自希拉里的小猫");
        gift.setGiftNum(3);
        gift.setGiftCount(5);
        gift.setPriority(2);
        giftDao.insertOne(gift);
    }

    @Test
    public void testDeleteGift() {
        giftDao.deleteOne(new Gift(2));
    }

    @Test
    public void testGetGiftListByClass() {
        List<Gift> giftList = giftDao.getGiftListByClass(new Class(1));
        System.out.println(giftList);
    }

    @Test
    public void testGetGiftById() {
        Gift gift = giftDao.getOneById(new Gift(1));
        System.out.println(gift);
    }

    @Test
    public void testUpdateGift() {
        Gift gift = giftDao.getOneById(new Gift(4));
        System.out.println(gift);
        gift.setGiftDesc("其实我是小狗小狗");
        giftDao.updateOne(gift);
    }

}
