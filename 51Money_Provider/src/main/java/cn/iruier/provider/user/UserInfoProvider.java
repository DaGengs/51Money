package cn.iruier.provider.user;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.UserInfo;
import cn.iruier.mapper.user.UserInfoMapper;
import cn.iruier.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoProvider implements UserInfoService {

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public ResultVo updateInfo(UserInfo userInfo) {
        return ExecuteUtil.getResult(mapper.updateInfo(userInfo), "资料提交");
    }

    @Override
    public ResultVo checkStatus(int user_id) {
        int status = 0;
        if (mapper.queryByUid(user_id) != null) {
            status = mapper.queryByUid(user_id).getStatus();
        }
        return new ResultVo(0, status+"",null);
    }

    @Override
    public UserInfo queryByUid(int user_id) {
        return mapper.queryByUid(user_id);
    }
}
