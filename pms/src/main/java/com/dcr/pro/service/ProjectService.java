package com.dcr.pro.service;

import com.dcr.cust.bean.Customer;
import com.dcr.pro.bean.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectService {
    void insertPro(Project project);

    List<Project> getProjectList();

    Project getProjectByPid(Integer pid);

    List<Project> search(Integer cid,String keyword,Integer orderby);

    void edit(Project project);

    Boolean batchDel(Integer[] ids);

    List<Project> getProListWithoutAnalyze();
}
