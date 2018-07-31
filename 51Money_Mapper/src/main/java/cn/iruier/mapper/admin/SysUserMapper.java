package cn.iruier.mapper.admin;

import cn.iruier.entity.admin.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/30 21:17
 */
public interface SysUserMapper {

    int insert(SysUser sysUser);

    SysUser queryByName(String name);

    List<SysUser> queryByPage(@Param("index")int index, @Param("size")int size);

    int deleteBatch(int[] user_ids);

    int queryCount();
}
