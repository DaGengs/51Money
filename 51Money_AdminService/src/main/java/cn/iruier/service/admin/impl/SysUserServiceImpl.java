package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysUser;
import cn.iruier.mapper.admin.SysUserMapper;
import cn.iruier.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/30 23:22
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResultVo insert(SysUser sysUser) {
        return ExecuteUtil.getResult(sysUserMapper.insert(sysUser), "新增用户");
    }

    @Override
    public SysUser login(String name) {

        return sysUserMapper.queryByName(name);
    }

    @Override
    public PageVo<SysUser> queryByPage(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(sysUserMapper.queryByPage(index, size), sysUserMapper.queryCount());
    }

    @Override
    public ResultVo deleteBatch(int[] user_ids) {
        return ExecuteUtil.getResult(sysUserMapper.deleteBatch(user_ids), "删除用户");
    }
}
