layui.config({
    base: 'lib/'
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['treetable'], function () {
    var treetable = layui.treetable;

});
layui.use(['form', 'table', 'tree', 'treetable'], function(){
    var form = layui.form, table = layui.table, treetable = layui.treetable;

    treetable.render({
        treeColIndex: 2,          // 树形图标显示在第几列
        treeSpid: 0,             // 最上级的父级id
        treeIdName: 'menu_id',       // id字段的名称
        treePidName: 'parent_id',     // pid字段的名称
        treeDefaultClose: true,   // 是否默认折叠
        treeLinkage: false,        // 父级展开时是否自动展开所有子级
        elem: '#tbdata',
        url: '/sys/menu/getTree.do',
        cols: [[
            {type: 'numbers'},
            {field: 'menu_id', align:'center',title: '菜单ID'},
            {field: 'name', width:'15%', title: '菜单名称'},
            {field: 'url', align:'center',title: '菜单路径',templet: function(d){return d.url == '' ? "无" : d.url}},
            {field: 'perms', align:'center',title: '权限',templet: function(d){return d.perms == '' ? "无" : d.perms}},
            {field: 'type', align:'center',title: '类型',templet: function (obj) {
                    var r;
                    switch (obj.type) {
                        case 0:
                            r = "<span class='layui-badge layui-bg-orange'>目录</span>";
                            break;
                        case 1:
                            r = "<span class='layui-badge layui-bg-green'>菜单</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-blue'>按钮</span>";
                            break;
                    }
                    return r;
                }},
            {field: 'icon', align:'center',title: '菜单图标', templet: '<i class="layui-icon">{{ d.icon }}</i>'},
            {field: 'order_num', align:'center',title: '排序规则'},
            {field: 'parent_id', align:'center',title: '父级菜单ID'},
            {fixed: 'right', width:150,title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });

  /*  table.render({
        elem: '#tbdata'
        ,url: '/sys/menu/list.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'menu_id', align:'center',title: 'ID', fixed: 'left'}
            ,{field: 'name', align:'center',title: '菜单名称',}
            ,{field: 'url',align:'center', title: '菜单路径',templet: function(d){return d.url == '' ? "无" : d.url}}
            ,{field: 'perms', align:'center',title: '权限',templet: function(d){return d.perms == '' ? "无" : d.perms}}
            ,{field: 'type', align:'center',title: '类型',templet: function (obj) {
                    var r;
                    switch (obj.type) {
                        case 0:
                            r = "<span class='layui-badge layui-bg-orange'>目录</span>";
                            break;
                        case 1:
                            r = "<span class='layui-badge layui-bg-green'>菜单</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-blue'>按钮</span>";
                            break;
                    }
                    return r;
                }}
            ,{field: 'icon', align:'center',title: '菜单图标', templet: '<i class="layui-icon">{{ d.icon }}</i>'}
            ,{field: 'order_num', align:'center',title: '排序规则'}
            ,{fixed: 'right', width:150,title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });
*/
    var active = {
        addMenu: function () {
            //示范一个公告层
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                area: '400px'
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
                    var menu = {
                        menu_name : $("#menu_name").val(),
                        menu_p_id : $("#menu_p_id").val(),
                        menu_url : $("#menu_url").val(),
                        menu_icon : $("#menu_icon").val()
                    };

                    $.ajax({
                        url: "saveMenu.do",
                        type: "post",
                        contentType : "application/json",
                        data: JSON.stringify(menu),
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.message);
                            } else {
                                layer.msg(data.message);
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
   /* initData();
    function initData() {
        $.ajax({
            url: "menuQueryAll.do",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    $("#menu_p_name").append("<option value='"+data[i].menu_id+"'>"+data[i].menu_name+"</option>");
                }
                form.render("select");
            }
        })
    }*/
});
