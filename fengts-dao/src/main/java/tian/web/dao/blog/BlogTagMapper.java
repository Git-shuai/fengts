package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.blog.BlogTag;
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
public interface BlogTagMapper extends BaseMapper<BlogTag> {
    /**
     * 批量添加标签
     * @param blogTagList
     * @return
     */
    public int addBlogTagBatch(List<BlogTag> blogTagList);

    List<Map<String, Object>> selectTag();

    IPage<Map<String, Object>> selectBlogListByTag(Page page,@Param("tagId") String tagId);
}
