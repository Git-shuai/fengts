package tian.web.bean.user;

import java.io.Serializable;
import lombok.Data;

/**
 * users
 * @author 
 */
@Data
public class Users implements Serializable {
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

    private static final long serialVersionUID = 1L;
}