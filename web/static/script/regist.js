


function $(id) {
    return document.getElementById(id);
}

function preRegist() {
    // BOM : Browser Object Model
    // var uname1 = document.forms.item[0].uname;


    // DOM : Document Object Model
    // 用户名应为6~16位数字和字母组成
    // 验证用户名
    var unameTxt = $("unameTxt");
    var unameSpan = $("unameSpan");
    var unameReg = /[0-9a-zA-Z]{6,16}/gim;
    var uname = unameTxt.value;
    if (!unameReg.test(uname)) {
        // 显示错误提示
        unameSpan.style.visibility = "visible";
        // 阻止表单提交
        return false;
    } else {
        unameSpan.style.visibility = "hidden";
    }

    // 验证密码
    var pwdTxt = $("pwdTxt");
    var pwdSpan = $("pwdSpan");
    var pwdReg = /[\w\.]{8,16}/gim;
    var pwd = pwdTxt.value;
    if (!pwdReg.test(pwd)) {
        // 显示错误提示
        pwdSpan.style.visibility = "visible";
        // 阻止表单提交
        return false;
    } else {
        pwdSpan.style.visibility = "hidden";
    }

    // 验证两次密码一直
    var pwdSpan2 = $("pwdSpan2");
    if ($("pwdTxt").value != $("pwdText2").value) {
        pwdSpan2.style.visibility = "visible";
        return false;
    } else {
        pwdSpan2.style.visibility = "hidden";
    }


    // 验证邮箱
    var email = $("emailTxt").value;
    var emailSpan = $("emailSpan");
    var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!emailReg.test(email)) {
        emailSpan.style.visibility = 'visible';
        return false;
    } else {
        emailSpan.style.visibility = 'hidden';
    }


    return true;
}



// 如果响应发送异步请求, 需要一个关键的对象:XMLHttpRequest

var xmlHttpRequest;


function creatXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        // 符合DOM 2标准的浏览器, xmlHttpRequest创建方式
        xmlHttpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE 浏览器
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {}
        xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
    }
}
function checkUname(uname) {
    creatXMLHttpRequest();
    var url = 'user.do?operate=ckUname&uname='+uname;
    xmlHttpRequest.open("GET", url, true);
    // 设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    // 发送请求
    xmlHttpRequest.send();
}

/**
 * 接收到响应后调用的函数
 */
function ckUnameCB() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        // xmlHttpRequest.responseText 标识服务器端响应的文本
        var responseText = xmlHttpRequest.responseText;
        // 解析responseText {'uname' : '1'}
        if (responseText == "{'uname':'1'}") {
            alert("用户名已被占用");
        }
    }
}
