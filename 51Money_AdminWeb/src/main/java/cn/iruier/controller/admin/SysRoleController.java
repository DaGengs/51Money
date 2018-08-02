package cn.iruier.controller.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysRole;
import cn.iruier.service.admin.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 17:45
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/save")
    @RequiresPermissions({"sys:role:save"})
    public ResultVo saveRole(SysRole sysRole) {
        return sysRoleService.insert(sysRole);
    }

    @GetMapping("/list")
    @RequiresPermissions({"sys:role:list"})
    public PageVo<SysRole> queryByPage(int page, int limit) {
        return sysRoleService.queryByPage(page, limit);
    }

    @PostMapping("/update")
    @RequiresPermissions({"sys:role:update"})
    public ResultVo update(SysRole sysRole) {
        return sysRoleService.update(sysRole);
    }

    @GetMapping("/delete")
    @RequiresPermissions({"sys:role:delete"})
    public ResultVo deleteBatch(int[] role_ids) {
        return sysRoleService.deleteBatch(role_ids);
    }

    @GetMapping("/selectAll")
    @RequiresPermissions({"sys:role:select"})
    public List<SysRole> queryAll() {
        return sysRoleService.queryAll();
    }

    @GetMapping("/getuserRole.do")
    public List<String> queryRoleNameListByUid(int user_id) {
        return sysRoleService.queryRoleNameListByUid(user_id);
    }
}
