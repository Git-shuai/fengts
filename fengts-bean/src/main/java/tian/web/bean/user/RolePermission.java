package tian.web.bean.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * role_permission
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_permission")
public class RolePermission implements Serializable {
    /**
     * 角色权限id
     */
    @TableId(type = IdType.AUTO)
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