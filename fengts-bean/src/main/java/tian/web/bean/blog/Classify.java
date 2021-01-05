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
public class Classify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父分类
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 分类描述
     */
    private String des;


}
