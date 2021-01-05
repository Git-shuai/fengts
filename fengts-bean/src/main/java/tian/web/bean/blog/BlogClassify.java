package tian.web.bean.blog;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * blog_classify
 * @author FTS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_classify")
public class BlogClassify implements Serializable {
    /**
     * 文章分类id
     */
    private Long id;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 分类id
     */
    private Long classifyId;

    private static final long serialVersionUID = 1L;
}