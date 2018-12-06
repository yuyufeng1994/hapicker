//正则校验
var validateUtil = {
    checkEmail: function (str) {
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return reg.test(str);
    },
    checkAccount: function (str) {
        if (str.length > 50) {
            return false;
        }
        var reg = /^\w{5,17}$/
        return reg.test(str) || validateUtil.checkEmail(str);
    },
    checkPwd: function (str) {
        var reg = /^\w{5,17}$/
        return reg.test(str);
    }
}
var dateUtil = {
    getTodayString: function (incrDay) {
        if(incrDay == null){
            incrDay = 0;
        }
        var day = new Date()
        day.setTime(day.getTime() + incrDay * 24 * 60 * 60 * 1000);
        var year = day.getFullYear();       //年
        var month = day.getMonth() + 1;     //月
        var day = day.getDate();            //日
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + '-' + month + '-' + day;
    }
}
