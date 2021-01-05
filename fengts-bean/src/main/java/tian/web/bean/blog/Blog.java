package tian.web.bean.blog;

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
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客作者
     */
    private String auth;

    /**
     * 博客创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 博客更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 博客阅读量
     */
    private Long readNum;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客是否原创（0：原创，1:转载）
     */
    private Integer isOriginal;

    /**
     * 是否开启赞赏（0：开启，1:关闭）
     */
    private Integer isAdmire;

    /**
     * 是否开启评论（0：开启，1:关闭）
     */
    private Integer isComment;


}
