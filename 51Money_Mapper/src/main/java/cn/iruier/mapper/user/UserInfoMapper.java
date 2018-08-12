package cn.iruier.mapper.user;

import cn.iruier.entity.user.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoMapper {

    @Update("update t_userinfo set realName = #{realName}, idNumber = #{idNumber}, gender = #{gender}, birthday = #{birthday}, address = #{address}, idCardImg_pre = #{idCardImg_pre}, idCardImg_aft = #{idCardImg_aft}, status = 1 where user_id = #{user_id}")
    int updateInfo(UserInfo userInfo);

    @Select("select realName, idNumber, gender, birthday, status from t_userinfo where user_id = #{user_id}")
    @ResultType(UserInfo.class)
    UserInfo queryByUid(int user_id);

    @Select("select * from t_userinfo limit #{index}, #{size}")
    @ResultType(UserInfo.class)
    List<UserInfo> queryByPage(@Param("index") int index, @Param("size") int size);

    @Update("update t_userinfo set status = #{status} where user_id = #{user_id}")
    @ResultType(Integer.class)
    int update(@Param("user_id") int user_id,@Param("status") int status);

    @Select("select count(*) from t_userinfo")
    @ResultType(Integer.class)
    int queryCount();
}
