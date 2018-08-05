package cn.iruier.provider.user;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;
import cn.iruier.mapper.user.RiskMapper;
import cn.iruier.service.user.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:59
 */
@Service
public class RiskProvider implements RiskService {

    @Autowired
    private RiskMapper riskMapper;

    @Override
    public ResultVo insert(Risk risk) {
        switch (risk.getType()) {
            case 1:
                risk.setScore(100);
                break;
            case 2:
                risk.setScore(90);
                break;
            case 3:
                risk.setScore(90);
                break;
            case 4:
                risk.setScore(80);
                break;
            case 5:
                risk.setScore(70);
                break;
                default:
                    risk.setScore(0);
                    break;
        }
        risk.setUser_id(1);
        return ExecuteUtil.getResult(riskMapper.insert(risk), "风控资料提交");
    }

    @Override
    public List<Risk> queryByUid(int user_id) {
        return riskMapper.queryByUid(user_id);
    }


}
