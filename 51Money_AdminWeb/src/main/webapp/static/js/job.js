layui.use(['form', 'table'], function(){
    var form = layui.form, $ = layui.$, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: '/schedule/job/list.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'job_id', width:'5%',align:'center',title: '任务ID'}
            ,{field: 'bean_name', width:'10%',align:'center', title: 'bean名称',}
            ,{field: 'method_name', align:'center',title: '方法名称',}
            ,{field: 'params', align:'center',title: '参数'}
            ,{field: 'cron_expression', align:'center',title: 'CRON表达式'}
            ,{field: 'remark',  align:'center',title: '备注'}
            ,{field: 'create_time', align:'center',title: '创建时间'}
            ,{field: 'status', width:'6%',align:'center',title: '任务状态',templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        case 1:
                            r = "<span class='layui-badge layui-bg-orange'>未启动</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-blue'>运行中</span>";
                            break;
                        case 3:
                            r = "<span class='layui-badge layui-bg-red'>已暂停</span>";
                            break;
                    }
                    return r;
                }}
            ,{fixed: 'right', width:'20%',title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });


    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        var job_ids = [];
        job_ids.push(data.job_id);
        if (layEvent == 'begin') {
            layer.confirm('确认开始?', function(index){
                changeJob('run', job_ids);
            });
        } else if(layEvent == 'delete'){
            layer.confirm('是否确认删除任务?', function(index){
                changeJob('delete', job_ids);
            });
        } else if(layEvent == 'pause'){
            layer.confirm('是否确认暂停?', function(index){
                changeJob('pause', job_ids);
            });
        } else if(layEvent == 'resume'){
            layer.confirm('是否确认继续?', function(index){
                changeJob('resume', job_ids);
            });
        }
    });

    var active = {
        addJob: function () {
            //示范一个公告层
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                area: '500px;'
                ,
                shade: 0.8
                ,
                id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,
                btn: ['提交','关闭']
                ,
                btnAlign: 'c'
                ,
                moveType: 1 //拖拽模式，0或者1
                ,
                content: $("#addModel")
                ,
                yes: function (index, layero) {
                    $.ajax({
                        url: "/schedule/job/save.do",
                        type: "post",
                        // contentType : "application/json",
                        data: $("#formData").serialize(),
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.msg);
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    });
                },
                btn2: function(){
                    location.reload();
                }
            });
        }
    }

    $('#add').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });


    var active = {
        startBatch: function(){
            var checkStatus = table.checkStatus('tbdata')
                ,data = checkStatus.data;
            var job_ids = [];
            for (var i = 0; i < data.length; i++) {
                job_ids.push(data[i].job_id);
            }
            layer.confirm('确认开始所选任务?', function(index) {
                if (job_ids.length == 0) {
                    layer.msg("请先选择任务");
                    return false;
                }
                changeJob('run', job_ids);
            });
        },
        pauseBatch: function(){
            var checkStatus = table.checkStatus('tbdata')
                ,data = checkStatus.data;
            var job_ids = [];
            for (var i = 0; i < data.length; i++) {
                job_ids.push(data[i].job_id);
            }
            layer.confirm('确认暂停所选任务?', function(index) {
                if (job_ids.length == 0) {
                    layer.msg("请先选择任务");
                    return false;
                }
                changeJob('pause', job_ids);
            });
        },
        resumeBatch: function(){
            var checkStatus = table.checkStatus('tbdata')
                ,data = checkStatus.data;
            var job_ids = [];
            for (var i = 0; i < data.length; i++) {
                job_ids.push(data[i].job_id);
            }
            layer.confirm('确认继续所选任务?', function(index) {
                if (job_ids.length == 0) {
                    layer.msg("请先选择任务");
                    return false;
                }
                changeJob('resume', job_ids);
            });
        },
        delBatch: function(){
            var checkStatus = table.checkStatus('tbdata')
                ,data = checkStatus.data;
            var job_ids = [];
            for (var i = 0; i < data.length; i++) {
                job_ids.push(data[i].job_id);
            }
            layer.confirm('确认删除所选任务?', function(index) {
                if (job_ids.length == 0) {
                    layer.msg("请先选择任务");
                    return false;
                }
                changeJob('delete', job_ids);
            });
        },
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function changeJob(url, data) {
        $.ajax({
            url: "/schedule/job/" + url + ".do",
            data:  {job_ids: data},
            success: function (data) {
                layer.msg(data.msg);
                setInterval("location.reload()",1000);
            }
        })
    }

});