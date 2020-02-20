package com.dcr.usual.service.Impl;

import com.dcr.usual.bean.TieZi;
import com.dcr.usual.bean.TieZiExample;
import com.dcr.usual.dao.TieZiMapper;
import com.dcr.usual.service.TieZiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TieZiServiceImpl implements TieZiService {

    @Resource
    private TieZiMapper tieZiMapper;

    @Override
    public void saveInfo(TieZi tieZi) {
        tieZiMapper.insert(tieZi);
    }

    @Override
    public List<TieZi> getTieziLimitedList() {
        TieZiExample example = new TieZiExample();
        example.setOrderByClause("add_date desc limit 0,4");
        return tieZiMapper.selectByExample(example);
    }
}
