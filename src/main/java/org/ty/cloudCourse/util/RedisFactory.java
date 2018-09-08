package org.ty.cloudCourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.entity.BaseEntity;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * @author kangtaiyang
 * @date 2018/6/28
 */
public class RedisFactory {
    private JedisPoolConfig jedisPoolConfig;
    private JedisPool pool;
    private String HOST;
    private String PORT;
    private String PASSWD;
    //实体对象在redis中个数的哈希key
    private final String keyCount = "keyCount";
    @Autowired
    private DatabaseSyncUtil databaseSyncUtil;

    public Jedis getJedis() {
        //创建连接池
        pool = new JedisPool(jedisPoolConfig, HOST, Integer.parseInt(PORT), 0, PASSWD);
        //获得核心对象
        Jedis jedis = pool.getResource();
        return jedis;
    }

    public void closeJedis(Jedis jedis) {
        try {
            jedis.close();
        } catch (NullPointerException e) {
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <A> Map<String, String> hgetall(A a) {
        Jedis jedis = getJedis();
        System.out.println("正在从Redis中获取" + a);
        Map<String, String> map = jedis.hgetAll(a.getClass().getSimpleName() + ":" + ((BaseEntity) a).getId());
        closeJedis(jedis);
        return map;
    }

    //tuple里第一个是key 后面是map
    public String hmset(Tuple tuple) {
        Jedis jedis = getJedis();
        Map<String, String> result = (Map<String, String>) tuple.getB();
        Iterator iter = result.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> obj = (Map.Entry<String, String>) iter.next();
            if (obj.getValue().equals("null")) {
                iter.remove();
            }
        }

        System.out.println("result=" + result);
        String i = jedis.hmset(((String) tuple.getA()), result);
        System.out.println("Redis存储成功");
        incr((String) tuple.getA());
        closeJedis(jedis);
        System.out.println(tuple.getA() + " 已修改成功");
        return i;
    }

    /**
     * tuple第一个是type(如"Class"),第二个是key(如"Class:10")
     *
     * @param tuple
     * @return
     */
    public Long del(Tuple tuple) {
        Jedis jedis = getJedis();
        System.out.println("Redis删除" + tuple.getB() + "成功");
        long l = jedis.del("" + tuple.getB());
        decr("" + tuple.getA());
        closeJedis(jedis);
        return l;
    }

    /**
     * 获取该类在redis中的个数,单另存了一个list(keyCount)
     *
     * @param a
     * @return
     */
    public long getKeyCount(Class a) {
        Jedis jedis = getJedis();
        Long l = Long.parseLong(jedis.hget(keyCount, a.getClass().getSimpleName()));
        closeJedis(jedis);
        return l;
    }

    /**
     * 根据某对象查另一个对象集合。通过clazz反射确定key，fieldId确定field的值
     *
     * @param aClazz
     * @param field
     * @param fieldId
     * @param <A>
     * @return
     */
    public <A> List<A> queryAFromBid(Class aClazz, String field, Integer fieldId) {
        String key = aClazz.getSimpleName();
        Jedis jedis = getJedis();
        Set<String> keys = jedis.keys(key + ":*");
        System.out.println(keys);
        Map<String, Integer> map = new HashMap<>();
        for (String k :
                keys) {
            String o = jedis.hget(k, field);
            Integer v = null;
            if (o != null) {
                v = Integer.valueOf(o);
            }

            if (v != null) {
                map.put(k, v);
            }
        }
        Pipeline pipeline = jedis.pipelined();
        for (Map.Entry e :
                map.entrySet()) {
            pipeline.hgetAll("" + e.getKey());
        }
        List<Object> objList = pipeline.syncAndReturnAll();
        List<A> aList = new ArrayList<>();
        for (Object o :
                objList) {
            //这个o是符合条件的所有的hash对象的集合
            Map<String, String> hashList = (HashMap) o;
            A a = (A) databaseSyncUtil.hashTransforToClass(hashList, aClazz);
            aList.add(a);
        }
        System.out.println("Redis 已查出 " + key + " 的集合 ： " + aList);
        return aList;
    }

    public <A> long incr(Class a) {
        Jedis jedis = getJedis();
        Long l = jedis.hincrBy(keyCount, a.getClass().getSimpleName(), 1);
        closeJedis(jedis);
        return l;
    }

    public <A> long incr(String field) {
        Jedis jedis = getJedis();
        Long l = jedis.hincrBy(keyCount, field, 1);
        closeJedis(jedis);
        System.out.println(field + "个数已 +1");
        return l;
    }

    public <A> long decr(Class a) {
        Jedis jedis = getJedis();
        Long l = jedis.hincrBy(keyCount, a.getClass().getSimpleName(), -1);
        closeJedis(jedis);
        System.out.println(a.getClass().getSimpleName() + "个数已 -1");
        return l;
    }

    public long decr(String field) {
        Jedis jedis = getJedis();
        Long l = jedis.hincrBy(keyCount, field, -1);
        closeJedis(jedis);
        System.out.println(field + "个数已 -1");
        return l;
    }

    public RedisFactory(JedisPoolConfig jedisPoolConfig, String HOST, String PORT, String PASSWD) {
        this.jedisPoolConfig = jedisPoolConfig;
        this.HOST = HOST;
        this.PORT = PORT;
        this.PASSWD = PASSWD;
    }

    private RedisFactory() {

    }
}
