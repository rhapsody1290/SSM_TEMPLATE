package cn.apeius.usermanage.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Asus on 2016/11/2.
 */
@Table(name = "tb_user_role")
public class UserRole {
    @Id
    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
