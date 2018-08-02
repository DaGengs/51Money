package cn.iruier.service.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 17:09
 */
public interface SysMenuService {
    ResultVo insert(SysMenu sysMenu);

    PageVo<SysMenu> queryByPage(int page, int size);

    ResultVo update(SysMenu sysMenu);

    ResultVo deleteBatch(int[] menu_ids);

    List<SysMenu> queryAll();

    List<SysMenu> queryNotButtonList();

    List<SysMenu> queryTopMenuList();

    List<String> queryAllPermsByUid(int user_id);

    List<SysMenu> queryAllMenuList(int user_id);

    ResultVo queryTree();
}
