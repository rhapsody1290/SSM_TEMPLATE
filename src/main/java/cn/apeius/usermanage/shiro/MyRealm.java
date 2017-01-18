package cn.apeius.usermanage.shiro;

/**
 * Created by Asus on 2016/10/17.
 */
import cn.apeius.usermanage.domain.Resource;
import cn.apeius.usermanage.domain.Role;
import cn.apeius.usermanage.domain.User;
import cn.apeius.usermanage.service.RoleService;
import cn.apeius.usermanage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 验证当前登录的Subject，包括两部分：1、用户自己在数据库验证 2、在Shiro中密码验证
     * 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌，实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //用户名和密码
        String username = authcToken.getPrincipal().toString();
        String password = new String((char[])authcToken.getCredentials());
        //调用UserService中的login方法，用户名不存在或密码错误会抛出异常
        User user = userService.login(username,password);

        //传入的凭证为User对象，这样在用户登录后通过Subject.getPrincipal访问到User对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),getName());
        //从数据库中取出的经过加密，需要设置盐值，用户登录的密码经过md5+盐值加密后，与数据库返回的密码进行对比才能在Shrio中通过验证
        info.setCredentialsSalt(ByteSource.Util.bytes(username + "bupt"));
        return info;
    }

    /**
     * 为当前登录的Subject授予角色和权限
     * 调用Subject.hasRole或hasPermission方法时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        //在doGetAuthenticationInfo中返回的是principal是User对象
        User user = (User) principals.getPrimaryPrincipal();
        //从数据库获取该subject所拥有的角色名称集合
        List<Role> roles = roleService.queryRolesByUserId(user.getId());
        Set<String> roleNames = new HashSet<String>();
        for(Role role : roles)
            roleNames.add(role.getRoleName());
        //从数据库获得该subject所拥有的权限名称集合，这里希望将用户请求url与用户所能访问的url进行对比
        List<Resource> resources = roleService.queryResourceByUserId(user.getId());
        Set<String> permissions = new HashSet<String>();
        for(Resource resource : resources)
            permissions.add(resource.getUrl());
        //返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permissions);

        return info;
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        System.out.println("清除认证缓存");
        Cache<Object, AuthenticationInfo> c = getAuthenticationCache();
        //缓存的键值是用户名
        for(Object o : c.keys())
            System.out.println(o + " " + c.get(o));
        //取出User对象的user_name，删除键值
        User user = (User) principals.getPrimaryPrincipal();
        PrincipalCollection principalCollection = new SimplePrincipalCollection(user.getUserName(),getName());

        super.clearCachedAuthenticationInfo(principalCollection);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("清除授权缓存");
        Cache<Object, AuthorizationInfo> c = getAuthorizationCache();
        //缓存键是User对象
        for(Object o : c.keys())
            System.out.println(o + " " + c.get(o));
        super.clearCachedAuthorizationInfo(principals);
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }
}