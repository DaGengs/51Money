layui.use(['layer', 'form'], function () {
    var form = layui.form, $ = layui.jquery;

    $('#forgot').on('click', function () {
        layer.msg('请联系管理员.');
    });

    form.on('submit(login)', function (data) {
        $.ajax({
            url: "/sys/user/login.do",
            type: "post",
            data: {
                "username": $("#username").val(),
                "password": $("#password").val()
            },
            success: function (data) {
                if (data.code == 0) {
                    localStorage.setItem("user", data.data);
                    location.href = "index.html";
                } else {
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    });
});