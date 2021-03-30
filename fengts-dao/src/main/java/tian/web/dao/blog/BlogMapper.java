package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.blog.Blog;
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
public interface BlogMapper extends BaseMapper<Blog> {

    public int insertBlog(Map<String,Object> param);

    public int updateBlog(Map<String,Object> param);

    public IPage<Map<String,Object>> selectBlogList(Page page);

    public Map<String,Object> selectBlogById(@Param("blogId") String blogId);

    public IPage<Map<String,Object>> selectBlogListByParam(Page page,@Param("map") Map<String,Object> map);

    List<Map<String,Object>> selectBlogListOfEcharts();

    public IPage<Map<String, Object>> selectRecycleBlogList(Page<Blog> blogPage);

    int updateBlogBatch(List<Integer> batchIdList);

    Map<String,Object> selectBlogReadNum();
}
