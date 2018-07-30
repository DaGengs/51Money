package cn.iruier.mapper.user;

import cn.iruier.entity.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper {
    @Insert("insert into t_userinfo (user_id, realName, idNumber, gender, birthday, address, idCardImg_pre, idCardImg_aft, status) values(#{user_id}, #{realName}, #{idNumber}, #{gender}, #{birthday}, #{address}, #{idCardImg_pre}, #{idCardImg_aft}, 1)")
    int insert(UserInfo userInfo);

    @Select("select realName, idNumber, gender, birthday, status from t_userinfo where user_id = #{user_id}")
    @ResultType(UserInfo.class)
    UserInfo queryByUid(int user_id);
}
