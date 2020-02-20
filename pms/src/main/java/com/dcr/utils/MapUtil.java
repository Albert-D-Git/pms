package com.dcr.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtil {
    //将查询条件分离后拼接成字符串
    public static String parseParamMapToQueryString(Map<String, Object> paramMap) {
        Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            //append连续追加 如果第一个加&每循环一次可以把每组数据分开 而且在jsp页面上连接传参数时候不用加&
            sb.append("&").append("search_").append(key).append("=").append(value);
        }
        return sb.toString();
    }

    public static Map<String, Object> parseParamMapToMyBatisMap(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(key.equals("keyword")){
                value = "%"+value+"%";
            }
            resultMap.put(key,value);
        }
        return resultMap;
    }
}
