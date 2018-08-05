package cn.iruier.uicontroller.user;

import cn.iruier.core.oss.OSSUtil;
import cn.iruier.core.util.FileUtils;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;
import cn.iruier.service.user.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/3 17:05
 */
@RestController
public class RiskController {

    @Autowired
    private RiskService service;

    @Autowired
    private OSSUtil ossUtil;

    @PostMapping("riskCommit.do")
    public ResultVo uploadFile(MultipartFile file, int fileType) {
        Risk risk = new Risk();
        try {
            if (!ossUtil.isHave("RiskImgs/")) {
                ossUtil.createDir("RiskImgs/");
            }
            String fileUrl = ossUtil.fileUp("RiskImgs/"+ FileUtils.createFileName(file.getOriginalFilename()), file.getBytes());
            risk.setImgUrl(fileUrl);
            risk.setType(fileType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.insert(risk);
    }

    @GetMapping("riskList.do")
    public List<Risk> queryByAid() {
        return service.queryByUid(1);
    }

}
