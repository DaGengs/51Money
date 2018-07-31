package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysMenu;
import cn.iruier.mapper.admin.SysMenuMapper;
import cn.iruier.service.admin.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 17:34
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public ResultVo insert(SysMenu sysMenu) {
        return ExecuteUtil.getResult(sysMenuMapper.insert(sysMenu), "资源新增");
    }

    @Override
    public PageVo<SysMenu> queryByPage(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(sysMenuMapper.queryByPage(index, size), sysMenuMapper.queryCount());
    }

    @Override
    public ResultVo update(SysMenu sysMenu) {
        return ExecuteUtil.getResult(sysMenuMapper.update(sysMenu), "资源修改");
    }

    @Override
    public ResultVo deleteBatch(int[] menu_ids) {
        return ExecuteUtil.getResult(sysMenuMapper.deleteBatch(menu_ids), "资源删除");
    }

    @Override
    public List<SysMenu> queryAll() {
        return sysMenuMapper.queryAll();
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenu> queryTopMenuList() {
        return sysMenuMapper.queryTopMenuList();
    }

    @Override
    public List<SysMenu> queryListByParentId(int parent_id) {
        return sysMenuMapper.queryListByParentId(parent_id);
    }

    @Override
    public List<String> queryAllPermsByUid(int user_id) {
        return sysMenuMapper.queryAllPermsByUid(user_id);
    }

    @Override
    public List<Integer> queryAllMenuId(int user_id) {
        return sysMenuMapper.queryAllMenuId(user_id);
    }

    @Override
    public List<SysMenu> queryUserTop(List<Integer> menu_idList) {
        return sysMenuMapper.queryUserTop(menu_idList);
    }
}
