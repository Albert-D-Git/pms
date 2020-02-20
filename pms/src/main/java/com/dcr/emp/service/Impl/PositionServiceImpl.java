package com.dcr.emp.service.Impl;

import com.dcr.emp.bean.Position;
import com.dcr.emp.bean.PositionExample;
import com.dcr.emp.dao.PositionMapper;
import com.dcr.emp.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> getPositionList() {
        PositionExample example = new PositionExample();

        return positionMapper.selectByExample(example);
    }
}
