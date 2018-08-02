package cn.iruier.mapper.user;

import cn.iruier.entity.user.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoMapper {
    @Insert("insert into t_userinfo (user_id, realName, idNumber, gender, birthday, address, idCardImg_pre, idCardImg_aft, status) values(#{user_id}, #{realName}, #{idNumber}, #{gender}, #{birthday}, #{address}, #{idCardImg_pre}, #{idCardImg_aft}, 1)")
    int insert(UserInfo userInfo);

    @Select("select realName, idNumber, gender, birthday, status from t_userinfo where user_id = #{user_id}")
    @ResultType(UserInfo.class)
    UserInfo queryByUid(int user_id);

    @Select("select * from t_userinfo limit #{index}, #{size}")
    @ResultType(UserInfo.class)
    List<UserInfo> queryByPage(@Param("index") int index, @Param("size") int size);

    @Update("update t_userinfo set status = #{status} where user_id = #{user_id}")
    @ResultType(Integer.class)
    int update(@Param("user_id") int user_id,@Param("status") int status);
}
