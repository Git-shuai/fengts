package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tian.web.bean.blog.BlogClassify;
import tian.web.bean.blog.BlogTag;

import java.util.List;

/**
 * @author FTS
 */
public interface BlogClassifyDao extends BaseMapper<BlogClassify> {

    /**
     * 批量添加
     *
     * @param list
     */
    public void insertBatch(List<BlogClassify> list);
}