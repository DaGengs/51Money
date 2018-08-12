package cn.iruier.service.admin.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.util.StringUtils;
import cn.iruier.core.vo.MenuTree;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysMenu;
import cn.iruier.mapper.admin.SysMenuMapper;
import cn.iruier.service.admin.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<String> queryAllPermsByUid(int user_id) {
        List<String> userPerms = sysMenuMapper.queryAllPermsByUid(user_id);
        List<String> finalPerms = new ArrayList<>();
        for (int i = 0; i < userPerms.size(); i++) {
            String perms = userPerms.get(i);
            if (!StringUtils.isEmpty(perms)) {
                continue;
            }
            finalPerms.addAll(Arrays.asList(perms.split(",")));
        }
        return finalPerms;
    }

    @Override
    public List<SysMenu> queryAllMenuList(int user_id) {
        List<SysMenu> menuList = new ArrayList<>();
        if (user_id == 1) {
            menuList = sysMenuMapper.queryAllTop();
            for (int i = 0; i < menuList.size(); i++) {
                List<SysMenu> sysMenus = sysMenuMapper.queryListByParentId(menuList.get(i).getMenu_id());
                menuList.get(i).setChildren(sysMenus);
            }
            return menuList;
        }
        List<Integer> menuIdList = sysMenuMapper.queryAllMenuId(user_id);
        menuList = sysMenuMapper.queryUserTop(menuIdList);
        for (int i = 0; i < menuList.size(); i++) {
            List<SysMenu> sysMenus = sysMenuMapper.queryUserMenuByParentId(menuList.get(i).getMenu_id(), menuIdList);
            menuList.get(i).setChildren(sysMenus);
        }
        return menuList;
    }

    @Override
    public ResultVo queryTree() {
        return new ResultVo(0, "ok", sysMenuMapper.queryAll());
    }

    @Override
    public ResultVo getMenuTree() {
        List<MenuTree> menuTrees = sysMenuMapper.queryAllTopMenu();
        for (int i = 0; i < menuTrees.size(); i++) {
            List<MenuTree> menuTrees1 = sysMenuMapper.queryAllNextMenu(menuTrees.get(i).getValue());
            for (int j = 0; j < menuTrees1.size(); j++) {
                menuTrees1.get(j).setCheck(false);
                menuTrees1.get(j).setList(sysMenuMapper.queryAllPerms(menuTrees1.get(j).getValue()));
            }
            menuTrees.get(i).setCheck(false);
            menuTrees.get(i).setList(menuTrees1);
        }
        return new ResultVo(0,"获取成功",menuTrees);
    }
}
