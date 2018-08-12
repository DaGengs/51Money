layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: '/sys/loan/list.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', width:'5%',align:'center',title: '编号', fixed: 'left'}
            ,{field: 'type', width:'8%',align:'center', title: '借款类型', templet: function (obj) {
                    var r;
                    switch (obj.type) {
                        case 0:
                            r = "<span >信用贷</span>";
                            break;
                        case 1:
                            r = "<span >车易贷</span>";
                            break;
                        case 2:
                            r = "<span >房易贷</span>";
                            break;
                    }
                    return r;
                }}
            ,{field: 'money', align:'center',title: '借款金额',templet: function (obj) {
                    return obj.money/100;
                }}
            ,{field: 'rate', align:'center',title: '利息'}
            ,{field: 'monthes', align:'center',title: '借款期限'}
            ,{field: 'returnType', align:'center',title: '还款方式', templet: function (obj) {
                    var r;
                    switch (obj.returnType) {
                        case 0:
                            r = "<span >按月分期</span>";
                            break;
                        case 1:
                            r = "<span >按月到期</span>";
                            break;
                    }
                    return r;
                }}
            ,{field: 'disableDays', align:'center',title: '投标天数'}
            ,{field: 'title', align:'center',title: '标题'}
            ,{field: 'description', width: '20%',align:'center',title: '说明'}
            ,{field: 'createDate', width: '10%',align:'center',title: '申请日期'}
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
                    }
                    return r;
                }}
            ,{fixed: 'right', width:150,title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });


    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        if (layEvent == 'pass') {
            layer.confirm('是否确认通过?', function(index){
               auth(data.id, 1);
            });
        } else if(layEvent == 'refuse'){
            layer.confirm('是否确认拒绝?', function(index){
                auth(data.id, 2);
            });
        }
    });
    function auth(id, status) {
        $.ajax({
            url: "/sys/loan/update.do",
            data: {
                "id": id,
                "status": status
            },
            success: function (data) {
                layer.msg(data.msg);
                setInterval("location.reload()",1000);
            }
        })
    }
});

