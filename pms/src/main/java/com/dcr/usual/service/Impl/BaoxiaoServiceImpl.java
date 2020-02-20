package com.dcr.usual.service.Impl;

import com.dcr.emp.bean.Employee;
import com.dcr.usual.bean.Baoxiao;
import com.dcr.usual.bean.BaoxiaoExample;
import com.dcr.usual.dao.BaoxiaoMapper;
import com.dcr.usual.service.BaoxiaoService;
import com.dcr.utils.ConstantUtils;
import com.dcr.utils.MapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BaoxiaoServiceImpl implements BaoxiaoService {

    @Resource
    private BaoxiaoMapper baoxiaoMapper;

    @Override
    public PageInfo<Baoxiao> getBaoxiaoList(Integer eid, Integer pageNo, Map<String, Object> paramMap) {
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        Map<String,Object> myBatisMap = MapUtil.parseParamMapToMyBatisMap(paramMap);

        Object cid = myBatisMap.get("cid");
        if(cid != null){
            int id = Integer.valueOf(cid.toString());
            if(id != 0){
                criteria.andBxstatusEqualTo(id);
            }

        }

        Object keyword = myBatisMap.get("keyword");
        if(keyword != null){
            criteria.andBxremarkLike((String) keyword);
        }

        criteria.andEmpFkEqualTo(eid);
        PageHelper.startPage(pageNo, ConstantUtils.PAGE_SIZE);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);

        PageInfo<Baoxiao> pageInfo = new PageInfo<Baoxiao>(baoxiaos,ConstantUtils.NAVIGATE_PAGENUM);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(Baoxiao baoxiao, HttpSession session) {
        Employee emp = (Employee)session.getAttribute("loginUser");
        baoxiao.setEmpFk(emp.getEid());
        baoxiao.setBxid(UUID.randomUUID().toString());

        return baoxiaoMapper.insertSelective(baoxiao)>0?true:false;
    }

    @Override
    public boolean del(String id) {
        return baoxiaoMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean edit(Baoxiao baoxiao) {
        return baoxiaoMapper.updateByPrimaryKeySelective(baoxiao)>0?true:false;
    }


}
