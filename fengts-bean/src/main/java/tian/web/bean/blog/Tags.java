package tian.web.bean.blog;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父标签
     */

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long parentId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 描述
     */
    private String des;


}
