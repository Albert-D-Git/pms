package com.dcr.emp.controller;

import com.dcr.emp.bean.Position;
import com.dcr.emp.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {

    @Resource
    private PositionService positionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> getPositionList(){
        return positionService.getPositionList();
    }
}
