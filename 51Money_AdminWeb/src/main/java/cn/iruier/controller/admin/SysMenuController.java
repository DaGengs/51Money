package cn.iruier.controller.admin;

import cn.iruier.core.shiro.ShiroUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysMenu;
import cn.iruier.entity.user.User;
import cn.iruier.service.admin.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 22:46
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/save")
    @RequiresPermissions({"sys:menu:save"})
    public ResultVo insert(SysMenu sysMenu) {
        return sysMenuService.insert(sysMenu);
    }

    @GetMapping("/list")
    @RequiresPermissions({"sys:menu:list"})
    public PageVo<SysMenu> queryByPage(int page, int limit) {
        return sysMenuService.queryByPage(page, limit);
    }

    @PostMapping("/update")
    @RequiresPermissions({"sys:menu:update"})
    public ResultVo update(SysMenu sysMenu) {
        return sysMenuService.update(sysMenu);
    }

    @GetMapping("/delete")
    @RequiresPermissions({"sys:menu:delete"})
    public ResultVo deleteBatch(int[] menu_ids) {
        return sysMenuService.deleteBatch(menu_ids);
    }

    @GetMapping("/queryAll.do")
    @RequiresPermissions({"sys:menu:perms"})
    public List<SysMenu> queryAll() {
        return sysMenuService.queryAll();
    }

    @GetMapping("/select")
    @RequiresPermissions({"sys:menu:select"})
    public List<SysMenu> queryNotButtonList() {
        return sysMenuService.queryNotButtonList();
    }

    @GetMapping("/queryTopMenu.do")
    public List<SysMenu> queryTopMenuList() {
        return sysMenuService.queryTopMenuList();
    }

    @GetMapping("/queryAllPerms.do")
    public List<String> queryAllPermsByUid(int user_id) {
        return sysMenuService.queryAllPermsByUid(user_id);
    }

    @GetMapping("/getMenu.do")
    public List<SysMenu> queryAllMenuList() {
        return sysMenuService.queryAllMenuList(1);
    }

    @GetMapping("/getTree.do")
    public ResultVo getTree() {
        return sysMenuService.queryTree();
    }

    @GetMapping("/getMenuTree.do")
    public ResultVo getMenuTree() {
        return sysMenuService.getMenuTree();
    }
}
