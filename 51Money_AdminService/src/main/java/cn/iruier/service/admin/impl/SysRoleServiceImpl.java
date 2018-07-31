package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysRole;
import cn.iruier.mapper.admin.SysRoleMapper;
import cn.iruier.service.admin.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 17:22
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public ResultVo insert(SysRole sysRole) {
        return ExecuteUtil.getResult(sysRoleMapper.insert(sysRole), "新增角色");
    }

    @Override
    public PageVo<SysRole> queryByPage(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
       return PageUtil.getPageVo(sysRoleMapper.queryByPage(index, size), sysRoleMapper.queryCount());
    }

    @Override
    public ResultVo update(SysRole sysRole) {

        return ExecuteUtil.getResult(sysRoleMapper.update(sysRole), "修改角色");
    }

    @Override
    public ResultVo deleteBatch(int[] role_ids) {

        return ExecuteUtil.getResult(sysRoleMapper.deleteBatch(role_ids), "删除角色");
    }

    @Override
    public List<SysRole> queryAll() {

        return sysRoleMapper.queryAll();
    }

    @Override
    public List<String> queryRoleNameListByUid(int user_id) {

        return sysRoleMapper.queryRoleNameListByUid(user_id);
    }
}
