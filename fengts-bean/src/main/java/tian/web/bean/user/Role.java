package tian.web.bean.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}