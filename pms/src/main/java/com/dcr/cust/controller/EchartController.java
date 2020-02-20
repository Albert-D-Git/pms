package com.dcr.cust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echart")
public class EchartController {
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEchartData(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("IOS",2000);
        map.put("Android",3000);
        map.put("Sambian",500);
        map.put("windows",800);
        return map;
    }
}
