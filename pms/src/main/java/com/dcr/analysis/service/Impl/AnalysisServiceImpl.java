package com.dcr.analysis.service.Impl;

import com.dcr.analysis.bean.Analysis;
import com.dcr.analysis.bean.AnalysisExample;
import com.dcr.analysis.dao.AnalysisMapper;
import com.dcr.analysis.service.AnalysisService;
import com.dcr.pro.bean.Project;
import com.dcr.pro.bean.ProjectExample;
import com.dcr.pro.dao.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Resource
    private AnalysisMapper analysisMapper;
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public boolean insertProAna(Analysis analysis) {

        return analysisMapper.insert(analysis)>0?true:false;
    }

    @Override
    public List<Analysis> getAnalysisList() {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        List<Analysis> analyses = analysisMapper.selectByExample(example);

        for (Analysis an : analyses){
            Project project = projectMapper.selectByPrimaryKey(an.getId());
            an.setProject(project);
        }
        return analyses;
    }

    @Override
    public Analysis getAnalysisById(Integer id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(id);
        analysis.setProname(project.getPname());
        return analysis;
    }

    @Override
    public boolean updateAnalysisById(Analysis analysis) {
        analysis.setUpdatetime(new Date());
        return analysisMapper.updateByPrimaryKeySelective(analysis) > 0 ? true : false;
    }

    @Override
    public List<Analysis> search(Integer cid, String keyword, Integer orderby) {
        List<Analysis> list=new ArrayList<Analysis>();
        if(cid==0){
            List<Analysis> list1 = searchByProname(keyword, orderby);
            for (Analysis a:list1){
                list.add(a);
            }
            List<Analysis> list2 = searchByTitle(keyword, orderby);
            for (Analysis b:list2){
                for(int i = 0;i<list.size();i++){
                    if(b.getId()!= list.get(i).getId()){
                        list.add(b);
                    }
                }
            }
        }else if(cid==1){
            list =searchByProname(keyword,orderby);
        }else{
            list =searchByTitle(keyword,orderby);
        }
        return list;
    }

    @Override
    public boolean del(Integer[] ids) {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = analysisMapper.deleteByExample(example);
        return ids.length==i;
    }

    //cid==1 排序过了
    public List<Analysis> searchByProname(String keyword,Integer orderby){
        //可能有问题
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPnameLike("%"+keyword+"%");
        List<Project> proList = projectMapper.selectByExample(example);
        //可以以 id-pname存session域
        List<Integer> ids=new ArrayList<Integer>();
        for (Project pro:proList) {
            ids.add(pro.getPid());
        }
        AnalysisExample example1 = new AnalysisExample();
        AnalysisExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIdIn(ids);
        if(orderby==1){
            example1.setOrderByClause("addtime");
        }else if(orderby==2){
            example1.setOrderByClause("updatetime");
        }
        List<Analysis> list = analysisMapper.selectByExample(example1);
        for(Analysis an:list){
            Project project = projectMapper.selectByPrimaryKey(an.getId());
            an.setProject(project);
        }
        return list;
    }

    //cid==2 排序过了
    public List<Analysis> searchByTitle(String keyword,Integer orderby){
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+keyword+"%");
        if(orderby==1){
            //添加时间排序
            example.setOrderByClause("addtime asc");
        }else if(orderby==2) {
            example.setOrderByClause("updatetime asc");
        }
        List<Analysis> list = analysisMapper.selectByExample(example);
        for (Analysis an:list) {
            Project project = projectMapper.selectByPrimaryKey(an.getId());
            an.setProject(project);
        }
        return list;
    }
}
