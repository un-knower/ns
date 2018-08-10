package com.newsee.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具，用于将对象设置到redis中
 * 或者从redis中取对象
 * @author xiaoss add on 2017/6/16
 *
 */
public class SerializeUtil {
	
//	private static Logger logger = Logger.getLogger(SerializeUtil.class);
	
	/**
	 * 序列化对象成二进制数据
	 * @param object
	 * @return 二进制数组
	 */
	public static byte[] serialize(final Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
//        	logger.error("序列化object失败>>SerializeUtil>>serialize", e);
        }
        return null;
    }
	
	/**
     * 将二进制数组反序列化成对象
     * @param bytes
     * @return Object
     */
    public static Object unserialize(final byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
//        	logger.error("反序列化object失败>>SerializeUtil>>unserialize", e);
        } 
        return null;
    }
}
