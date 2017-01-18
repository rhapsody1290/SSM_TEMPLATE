package cn.apeius.usermanage.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by Asus on 2016/11/9.
 */
public class UrlPermissionResolver implements PermissionResolver{

    @Override
    public Permission resolvePermission(String permissionString) {
        if(permissionString.startsWith("/")){
            return new UrlPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }

}
