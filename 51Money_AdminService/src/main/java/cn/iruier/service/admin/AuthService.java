package cn.iruier.service.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;
import cn.iruier.entity.user.UserInfo;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/2 11:26
 */
public interface AuthService {
    PageVo<UserInfo> queryByPage(int page, int size);

    ResultVo update(int user_id, int status);

    PageVo<UserRiskVo> queryRisk(int page, int size);

    ResultVo updateRiskStatus(int user_id, int status);

    List<Risk> queryByUid(int user_id);
}
