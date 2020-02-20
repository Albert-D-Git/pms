package com.dcr.analyse.service;

import com.dcr.analyse.bean.Comparision;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ComparisionService {
    void saveInfo(Comparision comparision);

    List<Comparision> getComparisionList();


    PageInfo<Comparision> getAllComparisionList();
}
