package com.vc.app.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeUtil {
    /**
     * 序列化
     * 
     * @param object
     * @return
     * @throws Exception
     */
    public static byte[] serialize(Object object) throws Exception {
        try {
            // 序列化
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new Exception("序列化失败...", e);
        }
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object unserialize(byte[] bytes) throws Exception {
        try {
            // 反序列化
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new Exception("反序列化失败...", e);
        }
    }

    /**
     * Object 转为 json
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static String getJson(Object data) throws Exception {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new Exception("Json数据处理出错了...", e);
        }
    }
}