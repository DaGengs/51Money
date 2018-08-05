package cn.iruier.mapper.user;

import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Risk;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:50
 */
public interface RiskMapper {

    @Insert("insert into t_risk(user_id, type, imgUrl, status, score) values(#{user_id}, #{type}, #{imgUrl}, 0, #{score})")
    int insert(Risk risk);

    @Select("SELECT * from t_risk where user_id= #{user_id}")
    @ResultType(Risk.class)
    List<Risk> queryByUid(int user_id);

    @Select("SELECT u.user_id, u.realName, u.idNumber, r.type, r.imgUrl, r.score, r.status FROM t_userinfo u LEFT JOIN t_risk r ON u.user_id = r.user_id limit #{index}, #{size}")
    @ResultType(cn.iruier.core.vo.UserRiskVo.class)
    List<UserRiskVo> queryAll(@Param("index")int index, @Param("size")int size);

    @Select("select count(*) from t_risk")
    @ResultType(Integer.class)
    int queryCount();

    @Update("update t_risk set status = #{status} where user_id = #{user_id}")
    @ResultType(Integer.class)
    int update(@Param("user_id") int user_id,@Param("status") int status);
}
