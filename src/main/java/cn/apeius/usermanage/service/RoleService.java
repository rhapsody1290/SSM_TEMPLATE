package cn.apeius.usermanage.service;

import cn.apeius.usermanage.domain.Resource;
import cn.apeius.usermanage.domain.Role;
import cn.apeius.usermanage.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 2016/11/3.
 */
public interface RoleService {
    //角色表
    public Role queryRoleByRoleId(Long roleId);
    public void addRole(Role role);
    public void deleteRoleByRoleId(Long roleId);
    public void updateRole(Role role);

    //用户角色表
    public void addUserRole(Long userId, Long roleId);
    public void deleteUserRole(Long userId, Long roleId);

    //资源表
    public Resource queryResourceById(Long resourceId);
    public void addResource(Resource resource);
    public void deleteResourceByResourceId(Long resourceId);
    public void updateResource(Resource resource);

    //角色资源表
    public void addRoleResource(Long roleId, Long resourceId);
    public void deleteRoleResource(Long roleId, Long resourceId);

    //根据用户查询所有角色
    public List<Role> queryRolesByUserId(Long userId);
    //根据用户查询所有权限
    public List<Resource> queryResourceByUserId(Long userId);
    //根据角色查询所有资源
    public List<Resource> queryResourceByRoleId(Long roleId);
}
