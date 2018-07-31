package cn.iruier.mapper.admin;

import cn.iruier.entity.admin.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/30 21:59
 */
public interface SysMenuMapper {
    int insert(SysMenu sysMenu);

    List<SysMenu> queryByPage(@Param("index")int index, @Param("size")int size);

    int update(SysMenu sysMenu);

    int queryCount();

    int deleteBatch(int[] menu_ids);

    List<SysMenu> queryAll();

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取所有不包含按钮的菜单的上级菜单
     */
    List<SysMenu> queryTopMenuList();

    /**
     * 根据父菜单，查询子菜单
     */
    List<SysMenu> queryListByParentId(int parent_id);

    /**
     * 查询用户的所有权限
     */
    List<String> queryAllPermsByUid(int user_id);

    /**
     * 查询用户的所有菜单ID
     */
    List<Integer> queryAllMenuId(int user_id);

    /**
     * 查询用户所有菜单的上级菜单
     */
    List<SysMenu> queryUserTop(List<Integer> menu_idList);

   /* List<SysMenu> queryUserMenuByParentId(int menu_id, List<Integer> menu_idList);*/
}
