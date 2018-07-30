package cn.iruier.mapper.user;

import cn.iruier.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into t_user(username, password, phone, referrer) values(#{username}, #{password}, #{phone}, #{referrer})")
    int insert(User user);

    @Select("select id,username, password, phone, referrer from t_user")
    @ResultType(User.class)
    List<User> queryByPage(@Param("index") int index, @Param("size") int size);

    @Select("select id,username, password, phone, referrer from t_user where username = #{name} or phone = #{name}")
    @ResultType(User.class)
    User queryByName(String name);
}
