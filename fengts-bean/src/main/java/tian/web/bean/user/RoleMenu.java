package tian.web.bean.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户菜单关联表
 *
 * @author FTS36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu {

    /**
     * 用户菜单主键
     */
    private long id;
    /**
     * 用户id
     */
    private long roleId;
    /**
     * 菜单id
     */
    private long menuId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }


    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

}
