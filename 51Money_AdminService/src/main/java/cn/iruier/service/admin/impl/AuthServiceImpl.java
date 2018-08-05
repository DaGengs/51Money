package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;
import cn.iruier.entity.user.UserInfo;
import cn.iruier.mapper.user.RiskMapper;
import cn.iruier.mapper.user.UserInfoMapper;
import cn.iruier.service.admin.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/2 11:27
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    public UserInfoMapper userInfoMapper;

    @Autowired
    private RiskMapper riskMapper;

    @Override
    public PageVo<UserInfo> queryByPage(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(userInfoMapper.queryByPage(index, size), userInfoMapper.queryCount());
    }

    @Override
    public ResultVo update(int user_id, int status) {
        return ExecuteUtil.getResult(userInfoMapper.update(user_id, status), "信息审核");
    }
    @Override
    public PageVo<UserRiskVo> queryRisk(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(riskMapper.queryAll(index, size), riskMapper.queryCount());
    }

    @Override
    public ResultVo updateRiskStatus(int user_id, int status) {
        return ExecuteUtil.getResult(riskMapper.update(user_id, status), "风控资料审核");
    }
}
