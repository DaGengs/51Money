package cn.iruier.mapper.invest;

import cn.iruier.entity.invest.Pay;
import org.apache.ibatis.annotations.Insert;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:38
 */
public interface PayMapper {

    @Insert("insert into t_pay(invest_id, money, status, oid, createtime, paytime) values(#{invest_id}, #{money}, #{status}, #{oid}, now(), #{paytime})")
    int save(Pay pay);
}
