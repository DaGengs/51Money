package cn.iruier.service.user;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:59
 */
public interface RiskService {

    ResultVo insert(Risk risk);

    List<Risk> queryByUid(int user_id);


}
