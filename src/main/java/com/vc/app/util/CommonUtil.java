package com.vc.app.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用工具类
 */
public class CommonUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 生成短8位UUID
     * 
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer sb = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    /**
     * 根据用户名生成用户ID
     * 
     * @param username
     *            用户名
     * @return
     */
    public static String generateUserId(String username) {
        String uuid = UUID.nameUUIDFromBytes(username.getBytes()).toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成4位验证码
     * 
     * @return
     */
    public static String getForRand() {
        String str = "0,1,2,3,4,5,6,7,8,9";
        String str2[] = str.split(",");
        Random rand = new Random();
        int index = 0;
        String randStr = "";
        for (int i = 0; i < 4; ++i) {
            index = rand.nextInt(str2.length - 1);
            randStr += str2[index];
        }
        return randStr;
    }
    /**
     * 获得YYYYMMddHHmmss 格式的日期
     * @param date
     * @return
     */
    public static String getYYYYMMddHHmmss(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 从request中获取参数对应的值，参数不存在时，抛出异常
     * 
     * @param request
     *            请求对象
     * @param paramName
     *            参数名称
     * @return 参数的值，当参数值存在时，返回参数对应的值；否则，抛出异常
     * @throws IllegalArgumentException
     *             当参数名错误或者参数对应的值不存在时，抛出该异常
     */
    public static String getParamWithExp(HttpServletRequest request,
            String paramName) throws IllegalArgumentException {
        if (paramName == null || paramName.isEmpty()) {
            throw new IllegalArgumentException("Illegal argument: " + paramName
                    + " is null or empty");
        }

        String paramValue = getParamNoExp(request, paramName);

        if (paramValue == null || paramValue.isEmpty()) {

            throw new IllegalArgumentException("Illegal argument: " + paramName
                    + " is null or empty");
        }

        return paramValue;
    }

    /**
     * 从request中获取参数对应的值，参数不存在时，返回null，不抛出异常
     * 
     * @param request
     *            请求对象
     * @param paramName
     *            参数名称
     * @return 参数的值，当参数值存在时，返回参数对应的值；否则，返回null
     */
    public static String getParamNoExp(HttpServletRequest request,
            String paramName) {
        if (paramName == null || paramName.isEmpty()) {
            return null;
        }

        String paramValue = request.getParameter(paramName);
        // 从Session再取一次
        if (paramValue == null || paramValue.isEmpty()) {
            paramValue = (String) request.getSession().getAttribute(paramName);
        }
        return paramValue;
    }

    /**
     * 获取web项目根路径
     * 
     * @return 项目根路径
     */
    public static String getWebRoot() {
        String rootPath = System.getProperty("hor-server.root");
        return rootPath == null ? "/" : rootPath;
    }

    /**
     * 获取WEB-INF路径
     * 
     * @return WEB-INF路径
     */
    public static String getWebInf() {
        return getWebRoot() + "WEB-INF" + File.separator;
    }
}
