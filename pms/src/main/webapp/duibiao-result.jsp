<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
    <!--    1引入js-->
    <script type="text/javascript" src="static/js/echarts.js"></script>
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
</head>
<body>
<!--2创建容器-->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    $(function () {

        var names=[];
        var scores=[];
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/compare/list",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    names.push(item.company);
                    scores.push(item.targetmoney);
                })


                    //3初始化echart
                    var myChart = echarts.init(document.getElementById('main'));
                    //5.指定数据
                    var option = {
                        title: {
                            text: '各大手机品牌销售额'
                        },
                        tooltip: {},
                        legend: {
                            data:['销售额']
                        },
                        xAxis: {
                            data: names
                        },
                        yAxis: {
                            //设置单位两种方式
                            // axisLabel:{
                            //     formatter:'{value}万'
                            // }
                            axisLabel:{
                                formatter:function (value,index) {
                                    return value+"万";
                                }
                            }
                        },
                        series: [{
                            name: '销售额',
                            type: 'bar',
                            data: scores
                        }]
                    };

                    //4.给图标设置数据
                    myChart.setOption(option);
                }

        });

    });
</script>
</body>
</html>