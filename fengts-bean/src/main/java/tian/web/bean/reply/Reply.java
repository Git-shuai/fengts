package tian.web.bean.reply;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author FTS
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父评论
     */
    private Long parentId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 点子数
     */
    private Integer praiseNum;


    /**
     * 是否点赞
     */
    private Integer praiseStatus;

    /**
     * 评论开启
     */
    private Integer replyStatus;


}
