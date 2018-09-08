package org.ty.cloudCourse.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author kangtaiyang
 * @date 2018/7/2
 */
public class RedisListener implements ServletContextListener {
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil databaseSyncUtil;
    private Jedis jedis;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("数据库Redis-Mysql定时同步系统已加载");
        t.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        t.interrupt();
    }

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                System.out.println("数据库实现了同步");
                try {
                    System.out.println("..."+redisFactory);
                    System.out.println("......"+databaseSyncUtil);
                    jedis = redisFactory.getJedis();
             //       long classCount = redisFactory.getKeyCount(Class.class,jedis);
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}
