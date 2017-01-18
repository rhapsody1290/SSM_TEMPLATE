package cn.apeius.usermanage.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Asus on 2016/11/2.
 */
@Table(name = "tb_role_resource")
public class RoleResource {
    @Id
    private Long id;
    private Long roleId;
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
