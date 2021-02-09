package tian.web.dao.blog;

import tian.web.bean.blog.BlogClassify;
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
public interface BlogClassifyMapper extends BaseMapper<BlogClassify> {

    /**
     * 批量添加分类
     * @param blogClassifies
     * @return
     */
    public int addBlogClassifyBatch(List<BlogClassify> blogClassifies);
}
