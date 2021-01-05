package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tian.web.bean.blog.BlogTag;

import java.util.List;

/**
 * @author FTS
 */
public interface BlogTagDao extends BaseMapper<BlogTag> {

    /**
     * 批量添加
     *
     * @param list
     * @return int
     */
    public int insertBatch(List<BlogTag> list);
}