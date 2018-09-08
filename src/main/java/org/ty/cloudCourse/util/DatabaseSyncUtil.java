package org.ty.cloudCourse.util;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author kangtaiyang
 * @date 2018/7/2
 */
public class DatabaseSyncUtil {

    private RedisFactory redisFactory;
    private String HOST;
    private String PORT;
    private final String PACKAGE_ENTITY = "ty.cloudCourse.entity";

    /**
     * 实体类转为redis中的hash
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public Tuple classTransforToHash(BaseEntity obj) throws IllegalAccessException {
        redisFactory.getJedis();
        Tuple tuple = new Tuple();
        //这是key
        tuple.add(obj.getClass().getSimpleName() + ":" + obj.getId());
        Map<String, String> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        //计数器，遇到类中类直接一次性处理，避免单条处理
        int j = 0;
        //i=1是怕把类的id也存进去，id是key，不用重复存
        for (int i = 1; i < fields.length; i++) {
            Field f = fields[i];
            String str = f.getType().getTypeName();
            if (str.contains("lang")) {
                f.setAccessible(true);
                map.put(f.getName(), "" + f.get(obj));
            }
            if (str.contains(PACKAGE_ENTITY)) {
                if (j == 0) {
                    f.setAccessible(true);
                    Map<String, BaseEntity> map2 = obj.getParentEntity();
                    for (Map.Entry<String, BaseEntity> e :
                            map2.entrySet()) {
                        if (e.getValue() == null) {
                            map2.remove(e.getKey());
                        } else {
                            map.put(e.getKey(), "" + e.getValue().getId());
                        }
                    }
                    i++;
                }
            }
        }
        tuple.add(map);
        return tuple;
    }

    /**
     * hash转实体类
     *
     * @param map
     * @param a
     * @return
     */
    public Object hashTransforToClass(Map<String, String> map, java.lang.Class a) {
        //获取传入类的所有属性，如果不为基础类型则记录其名称
        //记载复杂类型属性
        List<String> strList = new ArrayList<>();
        for (Field f :
                a.getDeclaredFields()) {
            if (f.getType().getTypeName().contains(PACKAGE_ENTITY)) {
                strList.add(f.getName());
            }
        }
        Object obj = null;
        try {
            obj = a.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Set<Map.Entry<String, String>> fvs = map.entrySet();
        Iterator it = fvs.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry<String, String>) it.next();
            if (e.getValue().equals("null") || strList.contains(e.getKey())) {
                //如果发现复杂类型属性直接删除
                it.remove();
            }
        }
        try {
            BeanUtils.populate(obj, map);
            return obj;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 第二个参数是怕obj里面有复杂属性,所以按原库对比，如果有之前的存的就不设为null了
     *
     * @param map
     * @param m
     * @param a
     * @return
     */
    public Object hashTransforToClass(Map<String, String> map, Map<String, String> m, Object a) {
        //获取传入类的所有属性，如果不为基础类型则记录其名称
        List<String> strList = new ArrayList<>();
        for (Field f :
                a.getClass().getDeclaredFields()) {
            if (f.getType().getTypeName().contains(PACKAGE_ENTITY)) {
                strList.add(f.getName());
            }
        }

        Object obj = a;

        Set<Map.Entry<String, String>> fvs = map.entrySet();
        Iterator it = fvs.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry<String, String>) it.next();
            System.out.println(strList);
            if (e.getValue().equals("null")) {
                it.remove();
            }
            //如发现复杂类型属性，将它改为传进来的第二个参数的值
            if (strList.contains(e.getKey())) {
                if (m.containsKey(e.getKey())) {
                    e.setValue(m.get(e.getKey()));
                }
            }
        }
        try {
            BeanUtils.populate(obj, map);
            return obj;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 通过实体类获取a的类型和key
     *
     * @param entity
     * @return
     */
    public Tuple getTypeAndKeyFromEntity(BaseEntity entity) {
        String type = entity.getClass().getSimpleName();
        String key = type + ":" + entity.getId();
        return new Tuple(type, key);
    }

    public DatabaseSyncUtil(RedisFactory redisFactory, String HOST, String PORT) {
        this.redisFactory = redisFactory;
        this.HOST = HOST;
        this.PORT = PORT;
    }

    private DatabaseSyncUtil(){

    }
}