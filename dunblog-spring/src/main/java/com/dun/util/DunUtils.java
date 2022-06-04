package com.dun.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DunUtils {
    /**
     * 对象转化为Map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), (String) field.get(obj));
        }

        return map;
    }

    /**
     * 热力日期数据处理用工具
     * 将一个map中的key和value剥离形成新的map再放入list中
     */
    public static List<Map<String, Object>> mapToListMap(Map<String,Object> map,String key,String value) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();


        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put(key,entry.getKey());
            newMap.put(value,entry.getValue());
            result.add(newMap);
        }

        return result;
    }
}
