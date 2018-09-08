package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.GiftDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Gift;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.GiftService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;
import org.ty.cloudCourse.util.Tuple;

import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/7/7
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftDao giftDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    @Override
    public AllExecution queryGiftByClass(Class clazz) {
        List<Gift> giftList;
        try {
            giftList = redisFactory.queryAFromBid(Gift.class,"clazz",clazz.getClassId());
            if (giftList==null||giftList.size()==0){
                giftList = giftDao.getGiftListByClass(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(giftList);
    }
}
