package cn.apeius.usermanage.mapper;

import cn.apeius.usermanage.domain.Resource;
import cn.apeius.usermanage.domain.Role;
import cn.apeius.usermanage.domain.User;
import com.github.abel533.mapper.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.LongTypeHandler;

import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 2016/10/19.
 */
public interface RoleMapper extends Mapper<Role> {
    //获取某个用户的所有角色
    public List<Role> queryRolesByUserId(Long userId);

    //某个用户的所有权限
    public List<Resource> queryResourcesByUserId(Long userId);

    //查询角色的权限
    public List<Resource> queryResourceByRoleId(Long roleId);
}
