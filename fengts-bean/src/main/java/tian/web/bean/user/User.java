package tian.web.bean.user;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tian.web.TimeUtil;

/**
 * user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    /**
     * 用户表主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名（邮箱、手机）
     */
    private String username;

    /**
     * 密码（加密）
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 用户头像
     */
    private String headurl;

    /**
     * 新加字段 说明
     */
    private String des;


    /**
     * 创建时间
     */
    @JSONField(format = TimeUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = TimeUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS)
    private Date updateTime;

    /**
     * 删除状态（1已删除，0未删除）
     */
    @TableLogic(value = "0",delval = "1")
    @JSONField(serialize = false)
    private Integer del;

    /**
     * 新加字段 验证码
     */
    private String code;

    /**
     * 版本号
     */
    @Version
    @JSONField(serialize = false)
    private Integer version;

    private static final long serialVersionUID = 1L;
}