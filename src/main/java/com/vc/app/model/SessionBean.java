package com.vc.app.model;

import java.io.Serializable;

import com.vc.app.util.ApiSession;

public class SessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sessionKey;
    private int maxInactiveInterval;
    private ApiSession session;

    public SessionBean(String sessionKey, ApiSession session) {
        this.sessionKey = sessionKey;
        this.session = session;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    public ApiSession getSession() {
        return session;
    }

    public void setSession(ApiSession session) {
        this.session = session;
    }

}
