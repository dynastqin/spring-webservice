
package com.dynastqin.cxf.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * 高效率json数据转换框架：jackson
 * 
 * @author tantao
 * @date 2014-7-9 下午2:04:32
 * @id $Id$
 */
public class JacksonUtil {
    
    private final static ObjectMapper objMap=new ObjectMapper();
    
    public static <T> String entity2Json(T obj) {
    
        String json = null;
        try {
            json = objMap.writeValueAsString(obj);// 注意：日期在json数据中是long格式数据
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public static <K,V> String map2Json(Map<K,V> map) {
        String json = null;
        try {
            json= objMap.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public static <T> String list2Json(List<T> list) {
        String json = null;
        try {
            json= objMap.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public static <T> T json2Entity(String json,Class<T> clazz) {
    
        T obj = null;
        try {
            obj= objMap.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    public static <T> List<T> json2List(String json,Class<T[]> clazz){
    
        T[] objs=null;
        try {
            objs = objMap.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(objs);
    }
}
