package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.blog.BlogClassify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

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

    List<Map<String, Object>> classifyOfArticleNum();

    List<Map<String, Object>> selectClassify();

    IPage<Map<String, Object>> selectBlogListByClassify(Page page,@Param("classifyId") String classifyId);
}
