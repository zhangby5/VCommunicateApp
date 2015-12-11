package com.vc.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.vc.app.model.SessionBean;

public class CacheManager {

    private static Logger logger = Logger.getLogger(CacheManager.class);

    private static ShardedJedisPool shardedJedisPool;

    private static ShardedJedis getResource() {
        return shardedJedisPool.getResource();
    }

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(20);
        config.setMaxIdle(5);
        config.setMaxWait(1000l);
        config.setTestOnBorrow(false);

        Properties pro = FileUtil.loadProperties(CommonUtil.getWebInf()
                + "redis.properties");
        String host = pro.getProperty("host", "127.0.0.1");
        String port = pro.getProperty("port", "6379");
        String pwd = pro.getProperty("pwd", "bbw6379");
        boolean upwd = Boolean.valueOf(pro.getProperty("upwd", "false"));

        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo jShard = new JedisShardInfo(host, Integer.valueOf(port),
                "master");
        if (upwd) {
            jShard.setPassword(pwd);
        }
        shards.add(new JedisShardInfo(host, Integer.valueOf(port), "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);

    }

    public static void returnResource(ShardedJedis shardedJedis) {
        shardedJedisPool.returnResource(shardedJedis);
    }

    /**
     * 对应数据表id 为 key 行数据为值的结构·添加id对应的行数据（之前存在则覆盖，否则添加） jedis.setex(key,seconds,
     * value);
     * 
     * @param id_key
     * @param bean
     */
    public static void setHSession(String id_key, Integer timeToIdleSeconds,
            SessionBean session) {
        if (StringUtils.isBlank(id_key) || session == null) {
            return;
        }
        if (timeToIdleSeconds == null) {
            timeToIdleSeconds = 1800;
            session.setMaxInactiveInterval(timeToIdleSeconds);
        }
        ShardedJedis shardedJedis = getResource();
        try {
            shardedJedis.setex(id_key.getBytes(), timeToIdleSeconds,
                    SerializeUtil.serialize(session));
        } catch (Exception e) {
            logger.error("set cache session error:" + e.getMessage());
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 对应数据表id 为 key 行数据为值的结构·获得id对应的行数据
     * 
     * @param id_key
     * @return
     * @throws Exception
     */
    public static SessionBean getHSession(String id_key) {
        if (StringUtils.isBlank(id_key)) {
            return null;
        }

        ShardedJedis shardedJedis = getResource();
        byte[] sessionByte = shardedJedis.get(id_key.getBytes());
        SessionBean session = null;
        try {
            session = (SessionBean) SerializeUtil.unserialize(sessionByte);
        } catch (Exception e) {
            logger.error("get cache session error:" + e.getMessage());
        } finally {
            returnResource(shardedJedis);
        }
        return session;
    }
    
    /**
     * 对应数据表id 为 key 行数据为值的结构·获得id对应的行数据
     * 
     * @param id_key
     * @return
     * @throws Exception
     */
    public static void removeHSession(String id_key) {
        if (StringUtils.isBlank(id_key)) {
            return;
        }
        ShardedJedis shardedJedis = getResource();
        shardedJedis.decr(id_key.getBytes());
        returnResource(shardedJedis);
    }

    /**
     * 对应数据表名称为主key, 行数据id 为 次要key, 行数据为值的结构·设置hash结构的行数据
     * 
     * @param table_key
     * @param id_key
     * @param bean
     * @throws Exception
     */
    public static void setHTableListJson(String table_key, String id_key,
            Object bean) throws Exception {
        if (StringUtils.isBlank(table_key) || StringUtils.isBlank(id_key)
                || bean == null) {
            return;
        }
        ShardedJedis shardedJedis = getResource();
        shardedJedis.hset(table_key, id_key, SerializeUtil.getJson(bean));

        returnResource(shardedJedis);
    }

    /**
     * 对应数据表名称为主key, 行数据id 为 次要key, 行数据为值的结构·获得数据表主key对应的表数据
     * 
     * @param table_key
     * @return <id, json结构的行数据>
     */
    public static Map<String, String> getHTableListJson(String table_key) {

        Map<String, String> result = new HashMap<String, String>();

        if (StringUtils.isBlank(table_key)) {
            return result;
        }
        ShardedJedis shardedJedis = getResource();
        result = shardedJedis.hgetAll(table_key);
        returnResource(shardedJedis);
        return result;
    }

    /**
     * 对应数据表名称为主key, 行数据id 为 次要key, 行数据为值的结构·获得数据表主key以及id次要key对应的行数据
     * 
     * @param table_key
     * @param id_key
     * @return string 类型 json结构的行数据
     */
    public static String getHTableJson(String table_key, String id_key) {
        if (StringUtils.isBlank(table_key) || StringUtils.isBlank(id_key)) {
            return null;
        }
        ShardedJedis shardedJedis = getResource();
        String result = shardedJedis.hget(table_key, id_key);
        returnResource(shardedJedis);
        return result;
    }

    /**
     * 对应数据表名称为主key, 行数据id 为 次要key, 行数据为值的结构·删除数据表主key对应的表数据
     * 
     * @param table_key
     */
    public static void delHTableListJson(String table_key) {
        if (StringUtils.isBlank(table_key)) {
            return;
        }
        ShardedJedis shardedJedis = getResource();
        shardedJedis.hdel(table_key);
        returnResource(shardedJedis);
    }

    /**
     * 对应数据表名称为主key, 行数据id 为 次要key, 行数据为值的结构·删除数据表主key以及id次要key对应的行数据
     * 
     * @param table_key
     * @param id_key
     */
    public static void delHTableJson(String table_key, String id_key) {
        if (StringUtils.isBlank(table_key) || StringUtils.isBlank(id_key)) {
            return;
        }
        ShardedJedis shardedJedis = getResource();
        shardedJedis.hdel(table_key, id_key);
        returnResource(shardedJedis);
    }
}
