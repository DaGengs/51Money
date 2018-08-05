layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: '/sys/auth/list.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'user_id', width:'5%',align:'center',title: '用户ID', fixed: 'left'}
            ,{field: 'realName', width:'10%',align:'center', title: '真实姓名',}
            ,{field: 'idNumber', align:'center',title: '身份证号',}
            ,{field: 'gender', width:'5%', align:'center',title: '性别',templet: function (obj) {
                var r;
                    switch (obj.gender) {
                        case 1:
                            r = "<span>男</span>";
                            break;
                        case 2:
                            r = "<span>女</span>";
                            break;
                        default:
                            r = "<span></span>";
                            break;
                    }
                    return r;
            }}
            ,{field: 'birthday', width:'10%',align:'center',title: '出生日期'}
            ,{field: 'address', width:'20%',align:'center',title: '地址'}
            ,{field: 'idCardImg_pre', align:'center',title: '身份证正面', templet: function (obj) {
                    return "<img src='"+obj.idCardImg_pre+"' width='50px' height='50px' alt='未上传'/>"
                }}
            ,{field: 'idCardImg_aft', align:'center',title: '身份证反面', templet: function (obj) {
                    return "<img src='"+obj.idCardImg_aft+"' width='50px' height='50px' alt='未上传'/>"
                }}
            ,{field: 'status',width:'6%', align:'center',title: '审核状态',templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        case 0:
                            r = "<span class='layui-badge layui-bg-orange'>未认证</span>";
                            break;
                        case 1:
                            r = "<span class='layui-badge layui-bg-blue'>待审核</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-green'>已通过</span>";
                            break;
                        case 3:
                            r = "<span class='layui-badge layui-bg-red'>未通过</span>";
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
               auth(data.user_id, 2);
            });
        } else if(layEvent == 'refuse'){
            layer.confirm('是否确认拒绝?', function(index){
                auth(data.user_id, 3);
            });
        }
    });
    function auth(user_id, status) {
        $.ajax({
            url: "/sys/auth/update.do",
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