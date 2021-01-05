package tian.web.bean.blog;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class BlogClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 分类id
     */
    private Long classifyId;


}
