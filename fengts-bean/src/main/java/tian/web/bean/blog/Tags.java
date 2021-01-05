package tian.web.bean.blog;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tags
 * @author FTS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tags")
public class Tags implements Serializable {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 父标签
     */
    private Long parentId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 描述
     */
    private String des;

    private static final long serialVersionUID = 1L;
}