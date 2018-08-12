layui.config({
    base: 'lib/',
}).extend({
    authtree: 'authtree/authtree',
});

layui.use(['form', 'table', 'authtree'], function(){
    var form = layui.form, table = layui.table, authtree = layui.authtree;

    table.render({
        elem: '#tbdata'
        ,url: '/sys/role/list.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'role_id', align:'center',title: '角色ID', fixed: 'left'}
            ,{field: 'role_name', align:'center',title: '角色名称',}
            ,{field: 'remark', align:'center',title: '说明'}
            ,{field: 'create_user_id', align:'center',title: '创建人ID',templet: function(d){return d.create_user_id == '' ? "无" : d.create_user_id}}
            ,{field: 'create_time', align:'center',title: '创建时间'}
            ,{fixed: 'right', width:150,align:'center',title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });

    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        $("#role_name").val(data.role_name);
        $("#remark").val(data.remark);
        if (layEvent == 'edit') {
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                fixed: false
                ,
                area: '800px'
                ,
                offset: '100px'
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
                content: $("#menuModel")
                ,
                yes: function (index, layero) {
                    console.log(menu_ids);
                 /*   var ids = {role_id: data.role_id, menu_ids: menu_ids};
                    $.ajax({
                        url: "updateMyMenu.do",
                        type: "post",
                        data:ids,
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.message);
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    })*/
                },
                btn2: function(){
                    location.reload();
                }
            });
        } else if(layEvent == 'del'){ //删除
            layer.confirm('是否确认删除?', function(index){
                $.ajax({
                    url: "deleteUser.do",
                    data:"user_id="+data.user_id,
                    success: function(data){
                        if(data.code==1){
                            obj.del();//删除表格中的对应行数据
                            layer.close(index);
                            layer.msg(data.message, {icon: 5});
                        }else{
                            layer.msg(data.message, {icon: 6});
                        }
                    }
                });
            });
        }
    });

    var active = {
        addRole: function () {
            $("#formData")[0].reset();
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                area: '800px'
                ,
                fixed: false
                ,
                shade: 0.8
                ,
                offset: '100px'
                ,
                id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,
                btn: ['提交','关闭']
                ,
                btnAlign: 'c'
                ,
                moveType: 1 //拖拽模式，0或者1
                ,
                content: $("#menuModel")
                ,
                yes: function (index, layero) {
                    var role_name = $("#role_name").val();
                    var remark = $("#remark").val();

                    if (role_name == "") {
                        layer.msg("请输入角色名称");
                        return false;
                    }
                    if (remark == "") {
                        layer.msg("请输入角色说明");
                        return false;
                    }

                    console.log(menu_ids);
                  /*  $.ajax({
                        url: "saveRole.do",
                        type: "post",
                        data: {
                            role_name: role_name,
                        },
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.message);
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });*/
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

    initData();

    function initData() {
        $.ajax({
            url: "/sys/menu/getMenuTree.do",
            success: function (data) {
                authtree.render('#menuList', data.data, {inputname: 'authids[]', layfilter: 'lay-check-auth', openall: false});
            }
        })
    };

    form.on('checkbox(lay-check-auth)', function(data){
        // 获取所有已选中节点
        menu_ids = authtree.getChecked('#menuList');

    });
});
