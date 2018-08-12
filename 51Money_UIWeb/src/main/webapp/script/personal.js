$(function() {
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

    $('#submitBtn').click(function(){

        $('#userInfoForm').ajaxSubmit({
            success: function (r) {
                if(r.code == 0){
                    layer.alert(r.msg, function(index){
                        //window.location.reload();
                        //console.log('回调。。。。。');
                        layer.close(index);
                    });
                    //console.log('不是回调。。。。。');
                }else{
                    layer.alert(r.msg);
                }

            }
        });
    });
});