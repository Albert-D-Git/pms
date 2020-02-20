package com.dcr.analysis.service;

import com.dcr.analysis.bean.Analysis;

import java.util.List;

public interface AnalysisService {
    boolean insertProAna(Analysis analysis);

    List<Analysis> getAnalysisList();

    Analysis getAnalysisById(Integer id);

    boolean updateAnalysisById(Analysis analysis);

    List<Analysis> search(Integer cid, String keyword, Integer orderby);

    boolean del(Integer[] ids);
}
