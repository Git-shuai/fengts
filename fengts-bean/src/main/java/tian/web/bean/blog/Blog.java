package tian.web.bean.blog;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author FTS36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog")
public class Blog implements Serializable {
    /**
     * 博客文章id
     */
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
    private Date createTime;

    /**
     * 博客更新时间
     */
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

    private static final long serialVersionUID = 1L;
}