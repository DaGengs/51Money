package cn.iruier.mapper.admin;

import cn.iruier.entity.admin.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/30 21:32
 */
public interface SysRoleMapper {
    int insert(SysRole sysRole);

    List<SysRole> queryByPage(@Param("index")int index, @Param("size")int size);

    int update(SysRole sysRole);

    int queryCount();

    int deleteBatch(int[] role_ids);

    List<SysRole> queryAll();

    List<String> queryRoleNameListByUid(int user_id);
}
