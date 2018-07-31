package cn.iruier.service.shiro;

import cn.iruier.core.shiro.ShiroUtil;
import cn.iruier.entity.admin.SysUser;
import cn.iruier.mapper.admin.SysMenuMapper;
import cn.iruier.mapper.admin.SysUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: iruier
 * @Date: 2018/7/31 11:28
 */
@Service
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        List<String> roleList = Arrays.asList("admin", "user");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        List<String> perms = sysMenuMapper.queryAllPermsByUid(sysUser.getUser_id());
        info.addStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        SysUser sysUser = sysUserMapper.queryByName(username);
        if (sysUser == null) {
            throw new UnknownAccountException("用户名不存在");
        } else if (!Objects.equals(password, sysUser.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确");
        } else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, password, getName());
            ShiroUtil.setSessionAttribute("sysUser", sysUser);
            return info;
        }
    }
}
