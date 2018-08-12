
$(function () {
    $.get("http://localhost:81/loan/getList.do",null,function (arr) {
        $("#ul1").html("");
        for(i=0;i<arr.length;i++){
            $("#ul1").append(" <li>\n" +
                "                                <div class=\"title\"><a href=\"investDetail.html?id="+arr[i].id+"\" ><i class=\"icon icon-zhai\" title=\"信用贷\"></i></a><a href=\"investDetail.html?id="+arr[i].id+"\" class=\"f18\" title=\""+arr[i].title+"\">"+arr[i].title+"</a></div>\n" +
                "                                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "<td width=\"260\">借款金额<span class=\"f24 c-333\">"+arr[i].money/100.0+"</span>元</td>\n" +
                "                                        <td width=\"165\">年利率<span class=\"f24 c-333\">"+(Math.floor(arr[i].rate*100))+"% </span></td>\n" +
                "                                        <td width=\"180\" align=\"center\">期限<span class=\"f24 c-orange\">"+arr[i].disableDays+"</span>天</td>\n" +
                "                                        <td><div class=\"circle\">\n" +
                "                                            <div class=\"left progress-bar\">\n" +
                "                                                <div class=\"progress-bgPic progress-bfb5\">\n" +
                "                                                    <div class=\"show-bar\"> 56.3% </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div></td>\n" +
                "                                        <td align=\"right\"><a class=\"ui-btn btn-gray\" href=\"#\">"+getType(arr[i].status)+"</a> </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </li>");
        }
    });

    var user = JSON.parse(sessionStorage.getItem("user"));

    /*获取登录用户账户信息*/
    $.ajax({
        url: "http://localhost:81/getAccount.do",
        data: {"user_id": user.id},
        xhrFields: {withCredentials: true},
        success: function (account) {
            sessionStorage.setItem("account", JSON.stringify(account));
        }
    });

    account = JSON.parse(sessionStorage.getItem("account"));

    new Vue({
        el:"#myMoney",
        data: {
            totalMoney: account.totalMoney
        }
    })
    $.ajax({
        url: "http://localhost:81/loan/detail.do",
        data: {
            "id": getData()
        },
        success: function (obj) {
            new Vue({
                el:"#loanData",
                data:{
                    loan :obj
                }
            })
            if(obj.status==1){
                $("#other").hide();
                $("#investCommit").show();
            }else {
                $("#other").show();
                $("#investCommit").hide();
            }
            var p = obj.completeMoney / obj.money;
            $("#process").css({ "width": p*200, "background-color": "#0caffe" });
        }
    });


})

    function save() {
        //验证余额是否够支付？
        var t=$("#tz").val();//投资金额
        var m = account.totalMoney;
        if (t.length == 0) {
            alert("请输入投资金额");
            return false;
        }
        var min = 0;
        min = $("#min").text();
        var max = 0;
        max = $("#max").text();
        if (parseFloat(t) < parseFloat(min)) {
            alert("投资金额少于起投金额");
            return false;
        }
        if (parseFloat(t) > parseFloat(max)) {
            alert("超出最大投资金额");
            return false;
        }
        if (parseFloat(max) == 0) {
            $("#a1").show();
            $("#a2").hide();
        }
        if(parseFloat(m)>parseFloat(t)){
            $.ajax({
                url: "http://localhost:81/saveInvest.do",
                type: "post",
                data: {
                    "loan_id": getData(),
                    "money": t,
                    "account_id": account.id
                },
                success: function (obj) {
                    if(obj.code==0){
                        alert("投资成功");
                        location.href="invest.html";
                    } else {
                        alert(obj.msg);
                    }
                }
            })
        }else {
            alert("余额不足，请充值");
            location.href="pay.html";
        }
    }

function getType(t) {
    var s="";
    switch (t){
        case 0: s="未审核";break;
        case 1: s="借款中";break;
        case 2: s="结束";break;
        case 3: s="还款中";break;

    }
    return s;
}
