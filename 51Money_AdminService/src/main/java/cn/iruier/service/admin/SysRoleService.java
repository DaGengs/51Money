package cn.iruier.service.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysRole;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 16:58
 */
public interface SysRoleService {
    ResultVo insert(SysRole sysRole);

    PageVo<SysRole> queryByPage(int page, int size);

    ResultVo update(SysRole sysRole);

    ResultVo deleteBatch(int[] role_ids);

    List<SysRole> queryAll();

    List<String> queryRoleNameListByUid(int user_id);
}
