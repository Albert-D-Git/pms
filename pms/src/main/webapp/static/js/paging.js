function queryDate(path) {
    $.ajax({
        type:"get",
        url:path,
        success:function (msg) {
            $("tr[name='list-tr']").remove();
            $("#pagediv").html("");
            //遍历列表数据
            $(msg.map.page.list).each(function (index,item) {

                //每次遍历要获取到当前报销的状态：
                var status = "未审批";
                if(item.bxstatus == 1){
                    status = "驳回";
                }else if (item.bxstatus == 2){
                    status = "已审批";
                }else if(item.bxstatus == 3){
                    status = "已付款";
                }

                var tr ="<tr name='list-tr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22'>"
                    +"<td><input name='id' type='checkbox' id='id' value='"+item.bxid+"' class='np'></td>"
                    +"<td>"+item.bxid+"</td>"
                    +"<td>"+item.totalmoney+"</td>"
                    +"<td align='center'> <a href=''><u>"+item.bxtime+"</u></a></td>"
                    +"<td>"+item.bxremark+"</td>"
                    +"<td>"+status+"</td>"
                    +"<td><a href='javascript:void(0)' style='pointer-events:none;color: grey'>编辑</a></td>"
                    +"</tr>";

                $("#pageTr").before(tr);
            });
            //追加分页信息
            var firstPage ="<a onclick='queryDate(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNo=1"+msg.map.queryStr+"'>首页</a>&nbsp;&nbsp;";
            var prepPage ="<a onclick='queryDate(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNo="+(msg.map.page.pageNum-1)+msg.map.queryStr+"'>上一页</a>&nbsp;&nbsp;";

            //中间的 1 2 3 4...
            $("#pagediv").append(firstPage).append(prepPage);
            $(msg.map.page.navigatepageNums).each(function (index,item) {
                if(item == msg.map.page.pageNum){
                    $("#pagediv").append("[<span style='color: red'>"+item+"</span>]&nbsp;");
                }else {
                    $("#pagediv").append("<a onclick='queryDate(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNo="+item+"'>"+item+"</a>&nbsp;");
                }

            });

            var nextPage ="<a onclick='queryDate(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNo="+(msg.map.page.pageNum+1)+msg.map.queryStr+"'>下一页</a>&nbsp;&nbsp;";
            var endPage ="<a onclick='queryDate(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNo="+msg.map.page.pages+msg.map.queryStr+"'>末页</a>&nbsp;&nbsp;";
            $("#pagediv").append(nextPage).append(endPage);
            $("#pagediv").append("&nbsp;&nbsp;<input onblur='changePageNum(this.value,this.name,this.id)' id='"+msg.map.queryStr+"' name='"+msg.map.requestURI+"' size='1px' >");
        }
    });
}

function changePageNum(pageNo,url,queryStr) {
    if(!/^[1-9]\d*$/.test(pageNo)){
        alert("请输入正整数！");
    }else{
        queryDate(url+"?pageNo="+pageNo+queryStr);//queryStr 拼接的时候最开头带了& 所以直接加上就行了
    }

}
