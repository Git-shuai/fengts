package tian.web.bean.user;

import java.io.Serializable;
import lombok.Data;

/**
 * role_permission
 * @author 
 */
@Data
public class RolePermission implements Serializable {
    /**
     * 角色权限id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long preId;

    private static final long serialVersionUID = 1L;
}