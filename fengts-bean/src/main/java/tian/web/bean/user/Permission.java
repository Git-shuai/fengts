package tian.web.bean.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * permission
 * @author fts
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("permission")
public class Permission implements Serializable {
    /**
     * 权限id
     */
    @TableId(type = IdType.AUTO)
    private Long perId;

    /**
     * 权限名称
     */
    private String perName;

    /**
     * url
     */
    private String perApiUrl;

    /**
     * url类型
     */
    private String perMethod;

    /**
     * 父权限id
     */
    private Long parentPerId;

    /**
     * 权限描述
     */
    private String des;


    private static final long serialVersionUID = 1L;

}