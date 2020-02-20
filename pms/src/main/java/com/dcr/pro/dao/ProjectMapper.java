package com.dcr.pro.dao;


import java.util.List;

import com.dcr.pro.bean.Project;
import com.dcr.pro.bean.ProjectExample;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    //增加的
    List<Project> findProject(@Param("cid") Integer cid, @Param("keyword") String keyword, @Param("orderby") Integer orderby);

    List<Project> getProListWithoutAnalyze();

    List<Project> selectAllPro(ProjectExample example);
}