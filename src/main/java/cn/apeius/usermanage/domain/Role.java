package cn.apeius.usermanage.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Asus on 2016/10/19.
 */
@Table(name = "tb_role")
public class Role {
    @Id
    private Long id;
    //角色名称
    private String roleName;
    //角色描述
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
