//正则校验
var validateUtil = {
    checkEmail: function (str) {
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return reg.test(str);
    },
    checkAccount: function (str) {
        if(str.length > 50){
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