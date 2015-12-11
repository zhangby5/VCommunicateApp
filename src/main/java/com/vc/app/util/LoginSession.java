package com.vc.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.vc.app.model.UserInfoVO;

public class LoginSession {

    public static void setSession(HttpServletRequest request,
            HttpServletResponse response, UserInfoVO user) throws Exception {
        ApiSession apiSession = new ApiSession(30 * 60);
        String cookieId = apiSession.getCookieId();
        String cookieV = cookieId + "|" + DES.encrypt(cookieId, null);
        apiSession.setAttribute(SessionConst.USER_SESSION, user);
        CookieUtil.setCookie(response, SessionConst.USER_SESSION, cookieV, -1,
                null, null, true);
        request.getSession().setAttribute(SessionConst.USER_SESSION, user);
    }
    
    public static void delSession(HttpServletRequest request,
            HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, SessionConst.USER_SESSION);
        request.getSession().removeAttribute(SessionConst.USER_SESSION);
    }

    public static boolean checkSession(HttpServletRequest request,
            boolean noSession) throws Exception {
        /* userSO为空且需要session时 */
        Object user = request.getSession().getAttribute(SessionConst.USER_SESSION);
        if (user != null || noSession) {
            return true;
        }
        //return false;
        
        String cookval = CookieUtil.getCookieValue(request, SessionConst.USER_SESSION);

        if(StringUtils.isBlank(cookval)) {
            return false;
        }
        String[] cs = cookval.split("\\|");
        if(cs == null || cs.length != 2) {
            return false;
        }
        
        try {
            if (DES.check(cs[0], cs[1])) {
                return true;
            } else if(!noSession) {
                return false;
            }
            ApiSession apiSession = ApiSession.get(cs[0]);
            if(apiSession == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}