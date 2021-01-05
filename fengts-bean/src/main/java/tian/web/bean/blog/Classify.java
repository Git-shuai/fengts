package tian.web.bean.blog;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * classify
 * @author FTS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("classify")
public class Classify implements Serializable {
    /**
     * 分类id
     */
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

    private static final long serialVersionUID = 1L;
}