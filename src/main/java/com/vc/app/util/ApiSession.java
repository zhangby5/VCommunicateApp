package com.vc.app.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.vc.app.model.SessionBean;

public class ApiSession implements Serializable {

    private static final long serialVersionUID = 1055965810150154404L;

    /** Session ID */
    private final String id;
    /** Session创建时间 */
    private long creationTime;
    /** Session最后一次访问时间 */
    private long lastAccessedTime;
    /** Session的最大空闲时间间隔·秒 */
    private int maxInactiveInterval;
    /** 是否是新建Session */
    private boolean newSession;
    /** Session前缀  */
    private static final String SESSION_KEY_PREFIX = "SESS_";
    /** Session key */
    private final String sessionKey;
    /** Session 属性集合 */
    private Map<String, Object> attributes;
    
    /** 获得attrSessionKey */
    private String getAttrSessionKey(String attrName) {
        return sessionKey + attrName;
    }
    
    /**
     * 创建新的Session。
     * 
     * @param maxIdleSeconds
     */
    public ApiSession(int maxIdleSeconds) {
        this.id = CommonUtil.generateShortUuid();
        long now = System.currentTimeMillis();
        this.creationTime = now;
        this.lastAccessedTime = now;
        this.maxInactiveInterval = maxIdleSeconds;
        this.newSession = true;

        this.sessionKey = SESSION_KEY_PREFIX + id;
        SessionBean sn = new SessionBean(sessionKey, this);
        sn.setMaxInactiveInterval(this.getMaxInactiveInterval());
        CacheManager.setHSession(sessionKey, this.getMaxInactiveInterval(), sn);
    }
    public String getCookieId() {
        return id;
    }
    /**
     * 更新 lastAccessedTime 。
     */
    public void refresh() {
        this.lastAccessedTime = System.currentTimeMillis();
        SessionBean sn = new SessionBean(sessionKey, this);
        sn.setMaxInactiveInterval(this.getMaxInactiveInterval());
        CacheManager.setHSession(sessionKey, this.getMaxInactiveInterval(), sn);
    }
    
    /**
     * 是否超时过期。
     * 
     * @param session
     * @return
     */
    public boolean isExpired() {
        String sessionKey = SESSION_KEY_PREFIX + id;
        SessionBean _this = CacheManager.getHSession(sessionKey);
        // 先查看缓存层面的超时控制
        if (_this == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        long last = this.getLastAccessedTime();
        long interal = now - last;
        if (interal > this.getMaxInactiveInterval()) {
            this.invalidate();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过Session id获取已经存在的Session，如果没有，返回null。
     * 
     * @return
     */
    public static ApiSession get(String id) {
        String sessionKey = SESSION_KEY_PREFIX + id;
        SessionBean sn = CacheManager.getHSession(sessionKey);
        ApiSession ret = sn.getSession();
        if (ret != null) {
            ret.newSession = false;
            ret.refresh();
        }
        return ret;
    }
    
    /**
     * 强制Session立即失效。
     */
    public synchronized void invalidate() {
        CacheManager.removeHSession(this.sessionKey);
    }
    
    /**
     * 移除属性。
     * 
     * @param attrName
     * @return
     */
    public synchronized void removeAttribute(String attrName) {
        String attrSessionKey = this.getAttrSessionKey(attrName);
        this.getAttributes().remove(attrSessionKey);
        this.refresh();
    }
    
    /**
     * 设置属性。
     * 
     * @param attrName
     * @param attrValue
     */
    public synchronized void setAttribute(String attrName, Object attrValue) {
        String attrSessionKey = this.getAttrSessionKey(attrName);
        this.getAttributes().put(attrSessionKey, attrValue);
        this.refresh();
    }

    /**
     * 获取属性的值。
     * 
     * @param attrName
     * @return
     */
    public Object getAttribute(String attrName) {
        this.refresh();
        String attrSessionKey = this.getAttrSessionKey(attrName);
        Object retObject = this.getAttributes().get(attrSessionKey);
        return retObject;
    }

    public int getMaxInactiveInterval() {
        if (maxInactiveInterval == -1) {
            maxInactiveInterval = 1800;
        }
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public boolean isNewSession() {
        return newSession;
    }

    public Map<String, Object> getAttributes() {
        if(attributes == null) {
            return new HashMap<String, Object>();
        }
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

}