package cn.iruier.uicontroller.user;

import cn.iruier.core.oss.OSSUtil;
import cn.iruier.core.util.FileUtils;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.UserInfo;
import cn.iruier.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private OSSUtil ossUtil;

    @PostMapping("commitInfo.do")
    public ResultVo saveInfo(@RequestBody UserInfo userInfo) {
        return service.save(userInfo);
    }

    @PostMapping("imgUpload.do")
    public ResultVo uploadFile(MultipartFile file) {
        try {
            if (!ossUtil.isHave("IDCardImgs/")) {
                ossUtil.createDir("IDCardImgs/");
            }

            String fileUrl = ossUtil.fileUp("IDCardImgs/"+ FileUtils.createFileName(file.getOriginalFilename()), file.getBytes());
            return ResultVo.setSuccess(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVo.setError("上传失败");
        }
    }

    @GetMapping("checkStatus.do")
    public ResultVo checkStatus(int user_id) {
        return service.checkStatus(user_id);
    }

    @GetMapping("getUserInfo.do")
    public UserInfo getUserInfo(int user_id) {
        UserInfo userInfo = service.queryByUid(user_id);
        return userInfo;
    }
}
