package cn.iruier.service.user;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.UserInfo;

public interface UserInfoService {
    ResultVo updateInfo(UserInfo userInfo);

    ResultVo checkStatus(int user_id);

    UserInfo queryByUid(int user_id);
}
