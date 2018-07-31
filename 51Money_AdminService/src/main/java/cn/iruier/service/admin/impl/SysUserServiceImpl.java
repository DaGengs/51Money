package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
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
        return null;
    }

    @Override
    public PageVo<SysUser> queryByPage(int page, int size) {
        PageVo<SysUser> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<SysUser> sysUsers = sysUserMapper.queryByPage(index, size);
        if (sysUsers != null) {
            pageVo.setCode(0);
            pageVo.setCount(sysUserMapper.queryCount());
            pageVo.setMsg("");
            pageVo.setData(sysUsers);
        } else {
            pageVo.setCode(1);
            pageVo.setMsg("暂无数据");
            pageVo.setCount(0);
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

    @Override
    public ResultVo deleteBatch(int[] user_ids) {
        return null;
    }
}
