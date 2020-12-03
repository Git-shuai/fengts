package tian.web.bean.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 用户表主键
     */
    private Long userId;

    /**
     * 用户名（邮箱、手机）
     */
    private String username;

    /**
     * 密码（加密）
     */
    private String password;

    /**
     * 用户头像
     */
    private String headurl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除状态（1已删除，0未删除）
     */
    private Integer delete;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}