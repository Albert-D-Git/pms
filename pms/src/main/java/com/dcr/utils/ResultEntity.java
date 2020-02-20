package com.dcr.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {
    //定义一个map
    private Map<String,Object> map = new HashMap<String,Object>();

    //将实体类对象返回
    public static ResultEntity success(){
        ResultEntity entity = new ResultEntity();
        entity.map.put("statusCodes",200);
        entity.map.put("message","成功");
        return entity;
    }

    public static ResultEntity error(){
        ResultEntity entity = new ResultEntity();
        entity.map.put("statusCodes",500);
        entity.map.put("message","失败");
        return entity;
    }

    //往实体类对象的map中赋值
    public ResultEntity put(String key,Object value){
        this.map.put(key, value);
        return this;//谁调用 返回谁
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
