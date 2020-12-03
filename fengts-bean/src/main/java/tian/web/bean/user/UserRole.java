package tian.web.bean.user;

import java.io.Serializable;
import lombok.Data;

/**
 * user_role
 * @author 
 */
@Data
public class UserRole implements Serializable {
    /**
     * 用户角色表id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}