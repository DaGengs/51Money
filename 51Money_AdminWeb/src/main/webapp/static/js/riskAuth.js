layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: '/sys/auth/riskList.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'user_id', width:'5%',align:'center',title: '用户ID', fixed: 'left'}
            ,{field: 'realName', width:'10%',align:'center', title: '真实姓名',}
            ,{field: 'idNumber', align:'center',title: '身份证号',}
            ,{field: 'type', align:'center',title: '资料类型',templet: function (obj) {
                    var r;
                    switch (obj.type) {
                        case 1:
                            r = "<span>房产证</span>";
                            break;
                        case 2:
                            r = "<span>车</span>";
                            break;
                        case 3:
                            r = "<span>股票</span>";
                            break;
                        case 4:
                            r = "<span>保单</span>";
                            break;
                        case 5:
                            r = "<span>工作证明</span>";
                            break;
                        default:
                            r = "<span>暂未上传</span>";
                            break;
                    }
                    return r;
                }}
            ,{field: 'imgUrl', align:'center',title: '资料照片', templet: function (obj) {
                    return "<img src='"+obj.imgUrl+"' width='50px' height='50px' alt='未上传'/>"
                }}
            ,{field: 'score', align:'center',title: '安全系数'}
            ,{field: 'status',width:'6%', align:'center',title: '审核状态',templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        case 0:
                            r = "<span class='layui-badge layui-bg-blue'>待审核</span>";
                            break;
                        case 1:
                            r = "<span class='layui-badge layui-bg-green'>已通过</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-red'>未通过</span>";
                            break;
                        default:
                            r = "<span class='layui-badge layui-bg-red'>未上传</span>";
                            break;
                    }
                    return r;
                }}
            ,{fixed: 'right', width:150,title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });


    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        console.log(data);
        if (layEvent == 'pass') {
            layer.confirm('是否确认通过?', function(index){
               auth(data.user_id, 1);
            });
        } else if(layEvent == 'refuse'){
            layer.confirm('是否确认拒绝?', function(index){
                auth(data.user_id, 2);
            });
        }
    });
    function auth(user_id, status) {
        $.ajax({
            url: "/sys/auth/updateRiskStatus.do",
            data: {
                "user_id": user_id,
                "status": status
            },
            success: function (data) {
                layer.msg(data.msg);
                setInterval("location.reload()",1000);
            }
        })
    }
});