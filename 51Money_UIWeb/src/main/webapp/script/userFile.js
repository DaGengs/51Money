$(function () {

    // 初始化Web Uploader
    var uploader = WebUploader.create(getOption('filePicker'))
        .on('fileQueued', function (file) {
            fileQueued(file, 'fileList', uploader);
        })
        .on('uploadSuccess', function (file, response) {
            uploadSuccess(file, response, 'uploadImage');
        })
        .on('uploadProgress', function (file, percentage) {
            uploadProgress(file, percentage);
        });


    //基本配置
    function getOption(pickId) {
        return {
            auto: true,// 选完文件后，是否自动上传。
            swf: 'script/webuploader-0.1.5/Uploader.swf',// swf文件路径
            server: 'http://localhost:81/riskCommit.do',// 文件接收服务端。
            fromData: {"fileType": 1},
            pick: {
                id: '#' + pickId
            },// 选择文件的按钮。可选。内部根据当前运行是创建，可能是input元素，也可能是flash
            accept: {// 只允许选择图片文件。
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        }
    }

    uploader.on('uploadBeforeSend', function (object, data, header) {
        /*携带参数*/
        data.fileType = $("#type").val();
    });

    //显示缩略图
    function fileQueued(file, listId, uploader) {
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' +
            '<div class="info">' + file.name + '</div>' +
            '</div>'
        );
        var $img = $li.find('img');
        // $list为容器jQuery实例
        var $list = $('#' + listId);
        $list.append($li);
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr('src', src);//给图片的地址赋值
        }, 300, 220);
    }

    //成功后的页面处理
    function uploadSuccess(file, response, imageInputId) {
        $('#' + file.id).addClass('upload-state-done');
        layer.msg("上传成功");
        $("#save").removeAttr("disabled");
    }

    function uploadProgress(file, percentage) {
        var $li = $('#' + file.id);
        var $percent = $li.find('.progress');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress" style="height:3px;">\n' +
                '  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="' + percentage * 100 + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + percentage * 100 + '%;">\n' +
                '    <span class="sr-only">' + percentage * 100 + ' Complete</span>\n' +
                '  </div>\n' +
                '</div>')
                .appendTo($li)
                .find('span');
        }
    }

    $("#save").click(function () {
        $("#form").hide();
        $("#saveBtn").hide();
        layer.alert("提交成功");
        location.reload();
    });

    var user = JSON.parse(sessionStorage.getItem("user"));

    $.ajax({
        url: "http://localhost:81/riskList.do",
        data: {"user_id": user.id},
        xhrFields: {withCredentials: true},
        success: function (riskInfo) {
            if (riskInfo != "") {
                $("#form").hide();
            }
            if (riskInfo == "") {
                $("#riskInfo").hide();
            }
            new Vue({
                el: '#riskInfo',
                data: {
                    risks: riskInfo,
                }
            });
        }
    });
})