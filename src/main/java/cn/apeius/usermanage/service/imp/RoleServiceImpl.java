package cn.apeius.usermanage.service.imp;

import cn.apeius.usermanage.domain.Resource;
import cn.apeius.usermanage.domain.Role;
import cn.apeius.usermanage.domain.RoleResource;
import cn.apeius.usermanage.domain.UserRole;
import cn.apeius.usermanage.mapper.ResourceMapper;
import cn.apeius.usermanage.mapper.RoleMapper;
import cn.apeius.usermanage.mapper.RoleResourceMapper;
import cn.apeius.usermanage.mapper.UserRoleMapper;
import cn.apeius.usermanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.RegionSpanIterator;

import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 2016/11/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public Role queryRoleByRoleId(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void deleteRoleByRoleId(Long roldId) {
        roleMapper.deleteByPrimaryKey(roldId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void addUserRole(Long userId, Long roleId) {
        UserRole record = new UserRole();
        record.setUserId(userId);
        record.setRoleId(roleId);
        userRoleMapper.insertSelective(record);
    }

    @Override
    public void deleteUserRole(Long userId, Long roleId) {
        UserRole record = new UserRole();
        record.setUserId(userId);
        record.setRoleId(roleId);
        userRoleMapper.delete(record);
    }

    @Override
    public Resource queryResourceById(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public void addResource(Resource resource) {
        resourceMapper.insertSelective(resource);
    }

    @Override
    public void deleteResourceByResourceId(Long resourceId) {
        resourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    public void updateResource(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
    }

    @Override
    public void addRoleResource(Long roleId, Long resourceId) {
        RoleResource record = new RoleResource();
        record.setRoleId(roleId);
        record.setResourceId(resourceId);
        roleResourceMapper.insertSelective(record);
    }

    @Override
    public void deleteRoleResource(Long roleId, Long resourceId) {
        RoleResource record = new RoleResource();
        record.setRoleId(roleId);
        record.setResourceId(resourceId);
        roleResourceMapper.delete(record);
    }

    @Override
    public List<Role> queryRolesByUserId(Long userId) {
        return roleMapper.queryRolesByUserId(userId);
    }

    @Override
    public List<Resource> queryResourceByUserId(Long userId) {
        return roleMapper.queryResourcesByUserId(userId);
    }

    @Override
    public List<Resource> queryResourceByRoleId(Long roleId) {
        return roleMapper.queryResourceByRoleId(roleId);
    }
}
