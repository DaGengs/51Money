package cn.iruier.mapper.invest;

import cn.iruier.entity.invest.Invest;
import org.apache.ibatis.annotations.Insert;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:28
 */
public interface InvestMapper {

    @Insert("insert into t_invest(money, loan_id, account_id, createtime, status) values(#{money}, #{loan_id}, #{account_id}, now(), #{status})")
    int save(Invest invest);
}
