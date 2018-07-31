package cn.iruier.service.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysUser;

/**
 * @Author: iruier
 * @Date: 2018/7/30 23:04
 */
public interface SysUserService {
    ResultVo insert(SysUser sysUser);

    SysUser login(String name);

    PageVo<SysUser> queryByPage(int page, int size);

    ResultVo deleteBatch(int[] user_ids);

}
