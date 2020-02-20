function parseDate(time){
    var starttime= new Date(time);
    var year= starttime.getFullYear();
    var month =  starttime.getMonth()+1;
    var day= starttime.getDate();
    if(month < 10){
        month = "0"+month;
    }
    if(day < 10){
        day = "0" +day;
    }
    starttime=year+"-"+month+"-"+day;
    return starttime;
}