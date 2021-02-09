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
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long blogId;

    /**
     * 标签id
     */
    private Long tagId;


}
