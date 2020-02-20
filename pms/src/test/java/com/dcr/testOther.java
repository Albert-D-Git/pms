package com.dcr;

import org.apache.ibatis.annotations.ResultType;

import java.util.List;
import java.util.Map;

public class testOther {
    static List{
        //4个参数定义在这里 或者写个工具类定义四个静态参数
    }
    public static void main(String[] args) {
        //当前发病人的密切接触者可能为其他个案的发病人
        for (Map<String, Object> map : allNodeList) {//A下面的所有人（有病没病都在）
            map1 = this.getPersonalCaseData(map.idCard);//主表根据身份证查Map
            if(map1.size()>0){
                aaa(map1);
            }else{
                continue;
            }
        }
    }
    public void aaa(Map<String,String> parentCirculationMap){
            parentId = GlobalFunc.toString(parentCirculationMap.get("id"));
            //节点信息
            nodeMap.put("id", GlobalFunc.toString(i));
            nodeMap.put("name", GlobalFunc.toString(parentCirculationMap.get("org_姓名")));
            nodeMap.put("attributes", "[{\'modularity_class\':" + categoryIdMap.get(GlobalFunc.toString(parentCirculationMap.get("org_与发病人关系"))) + "}]");
            nodeList.add(nodeMap);
            //联系
            linksMap.put("id", GlobalFunc.toString(i));
            linksMap.put("source", "0");
            linksMap.put("target", GlobalFunc.toString(i));
            i++;
            linkCirculationList = this.getPersonalRelationshipData(parentId);//对应子表的parentId 查出来的是一个类型为map的list
            for (linkOne:linkCirculationList) {
                parentCirculationMap2 = this.getPersonalCaseData(linkOne.idCard);//判断有病无病(主表有无这个人)
                if(parentCirculationMap2.size()>0){
                    aaa(linkOne);
                }else{
                    continue;
                }
                //1 linkone linkTwo
                //   linkOne---                     linkOne1 ---                 linkOne11
                //         linkOne2-- linkOne22       linkOne12 linkOne122          linkOne112  linkOne11....
            }

        }
    }

