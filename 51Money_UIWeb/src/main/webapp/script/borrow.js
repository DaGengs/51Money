
$(function(){
    var user = JSON.parse(sessionStorage.getItem("user"));

    $.ajax({
        url: "http://localhost:81/loan/getStatus.do",
        async: false,
        data: {
            "user_id": user.id
        },
        success: function (data) {

            sessionStorage.setItem("status", JSON.stringify(data));

            new Vue({
                el:"#statusData",
                data:{
                    sdata: data
                }
            })
        }
    })

    var status = JSON.parse(sessionStorage.getItem("status"));

    $.ajax({
        url: "http://localhost:81/loan/getLoan.do",
        data: {"account_id": status.account_id},
        success: function (data) {
            if (data != "") {
                location.href = "borrow_apply_result.html";
            }
        }
    });


    new Vue({
        el:"#carditMoney",
        data: {
            carditMoney: status.carditMoney
        }
    })
    $.ajax({
        url: "http://localhost:81/loan/getLoan.do",
        data: {"account_id": status.account_id},
        success: function (data) {
            if (data != "") {
                location.href = "borrow_apply_result.html";
            }
        }
    });

    $("#borrowForm").validate({
        rules : {
            bidRequestAmount:{
                required:true,
                number:true,
                min:100,
                max:150000
            },
            currentRate:{
                required:true,
                number:true,
                min:5,
                max:20
            },
            minBidAmount:{
                required:true,
                number:true,
                min:100
            },
            title:"required"
        },
        messages: {
            bidRequestAmount:{
                required:"请填写借款金额",
                number:"借款金额为数字",
                min:"借款金额最小为{0}",
                max:"借款金额最大为{0}"
            },
            currentRate:{
                required:"请填写借款利息",
                number:"借款利息为数字",
                min:"最低借款利息为{0}%",
                max:"最大借款利息不能超过{0}%"
            },
            minBidAmount:{
                required:"请填写最小投标金额",
                number:"最小投标金额为数字",
                min:"最小投标金额必须大于{0}"
            },
            title:"必须填写借款原因"
        },
        //错误提示信息加载的位置
        errorPlacement:function(label, element){
            label.appendTo(element.closest(".form-group")).addClass('form-control-static');
        },
        //自定义错误样式
        errorClass:"text-danger col-sm-3",
        //未通过验证,进行高亮处理或其他处理；
        highlight:function(input){
            $(input).closest(".form-group").addClass("has-error");
        },
        //通过验证,清除高亮效果或其他处理；
        unhighlight:function(input){
            $(input).closest(".form-group").removeClass("has-error");
        }
    });

    $("#commit").click(function () {
        $.ajax({
            url: "http://localhost:81/loan/save.do",
            type: "post",
            data: $("#borrowForm").serialize(),
            success: function (data) {
                if (data.code == 0) {
                    location.href = "borrow_apply_result.html";
                } else {
                    alert(data.msg);
                }
            }
        })
    })
});