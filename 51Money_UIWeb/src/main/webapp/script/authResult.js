$(function(){
    var user = JSON.parse(sessionStorage.getItem("user"));


    /*获取登录用户详细信息*/
    $.ajax({
        url: "http://localhost:81/getUserInfo.do",
        data: {"user_id": user.id},
        xhrFields: {withCredentials: true},
        success: function (userInfo) {
            new Vue({
                el: '#userInfo',
                data: {
                    realName: userInfo.realName,
                    gender: userInfo.gender,
                    idNumber: userInfo.idNumber,
                    birthday: userInfo.birthday,
                    username: user.username,
                    phone: user.phone,
                    status: userInfo.status
                }
            });
        }
    });

})
