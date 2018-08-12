$(function(){
    var user = JSON.parse(sessionStorage.getItem("user"));

    $.ajax({
        url: "http://localhost:82/getUser.do",
        xhrFields: {withCredentials: true},
        success: function (data) {
            $("#user_id").val(data.id);
            id = data.id;
            new Vue({
                el: '#user',
                data: {
                    username: data.username
                }
            });
        }
    });

    $.ajax({
        url: "http://localhost:81/checkStatus.do",
        data: {"user_id": user.id},
        success: function (data) {
            if (data.code == 1) {
                location.href = "real_auth_auditing.html";
            } else if (data.msg == 2) {
                location.href = "real_auth_result.html";
            }
        }
    });

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

    $("#submit").click(function () {
        var userInfo = $("#realAuthForm").serializeObject();
        $.ajax({
            url: "http://localhost:81/commitInfo.do",
            contentType : "application/json",
            type: "post",
            data: JSON.stringify($("#realAuthForm").serializeObject()),
            success: function (data) {
                if (data.code == 0 ) {
                    location.href = "real_auth_auditing.html";
                } else {
                    layer.alert(data.msg);
                }
            }
        })
    })

    // 初始化Web Uploader
    var uploader1 = WebUploader.create(getOption('filePicker1'))
        .on( 'fileQueued', function( file ) {
            fileQueued(file, 'fileList1',uploader1 );
        })
        .on( 'uploadSuccess', function( file, response ) {
            uploadSuccess(file, response, 'uploadImage1');
        })
        .on( 'error', function( handler ) {
            checkError(handler);
        });

    var uploader2 = WebUploader.create(getOption('filePicker2'))
        .on( 'fileQueued', function( file ) {
            fileQueued(file, 'fileList2',uploader2 );
        })
        .on( 'uploadSuccess', function( file, response ) {
            uploadSuccess(file, response, 'uploadImage2');
        })
        .on( 'error', function( handler ) {
            checkError(handler);
        });

    //基本配置
    function getOption(pickId){
        return {
            auto: true,// 选完文件后，是否自动上传。
            swf: 'script/webuploader-0.1.5/Uploader.swf',// swf文件路径
            server: 'http://localhost:81/imgUpload.do',// 文件接收服务端。
            fileNumLimit: 1,
            pick: {
                id:'#' + pickId,
                multiple: false
            },// 选择文件的按钮。可选。内部根据当前运行是创建，可能是input元素，也可能是flash
            accept: {// 只允许选择图片文件。
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        }
    }

    //显示缩略图
    function fileQueued( file , listId, uploader) {
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' +
            '<div class="info">' + file.name + '</div>' +
            '</div>'
        );
        var $img = $li.find('img');
        // $list为容器jQuery实例
        var $list = $('#' + listId);
        $list.append( $li );
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr( 'src', src );//给图片的地址赋值
        }, 300, 200 );
    }

    //成功后的页面处理
    function uploadSuccess( file, response, imageInputId ) {
        $( '#'+file.id ).addClass('upload-state-done');
        $('#' + imageInputId).val(response.msg);
    }

    //提交前检查
    function checkError(handler){
        if(handler == 'Q_EXCEED_NUM_LIMIT'){
            layer.alert('只能选择一个文件');
        }
    }
})
