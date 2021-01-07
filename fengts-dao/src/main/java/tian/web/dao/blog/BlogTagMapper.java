package tian.web.dao.blog;

import tian.web.bean.blog.BlogTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {
    /**
     * 批量添加标签
     * @param blogTagList
     * @return
     */
    public int addBlogTagBatch(List<BlogTag> blogTagList);
}
