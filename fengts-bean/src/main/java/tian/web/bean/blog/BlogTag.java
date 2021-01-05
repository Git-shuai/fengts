package tian.web.bean.blog;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * blog_tag
 * @author FTS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_tag")
public class BlogTag implements Serializable {
    /**
     * 文章标签表id
     */
    private Long id;

    /**
     * 文章id
     */
    private Long blogId;

    /**
     * 标签id
     */
    private Long tagId;

    private static final long serialVersionUID = 1L;
}