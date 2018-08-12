package cn.iruier.mapper.user;

import cn.iruier.entity.user.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:47
 */
public interface AccountMapper {

    @Insert("insert into t_account(user_id, totalMoney, redPackage, forceMoney, carditMoney, status) values(#{user_id}, #{totalMoney}, #{redPackage}, #{forceMoney}, #{carditMoney}, 1)")
    int insert(Account account);

    @Select("select * from t_account where user_id = #{user_id} and status = 1")
    @ResultType(Account.class)
    Account queryByUid(int user_id);

    @Update("update t_account set totalMoney = #{totalMoney}, redPackage = #{redPackage}, forceMoney = #{forceMoney}, carditMoney = #{carditMoney} where id = #{id}")
    int update(Account account);

    @Select("select * from t_account where id = #{account_id} and status = 1")
    @ResultType(Account.class)
    Account quertByAid(int account_id);
}
