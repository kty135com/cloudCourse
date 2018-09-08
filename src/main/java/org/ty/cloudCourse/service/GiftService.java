package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;

/**
 * @author kangtaiyang
 * @date 2018/7/7
 */
public interface GiftService extends BaseService {
    AllExecution queryGiftByClass(Class clazz);
}
