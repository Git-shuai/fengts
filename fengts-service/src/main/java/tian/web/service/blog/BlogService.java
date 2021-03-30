package tian.web.service.blog;

import tian.web.Result;
import tian.web.bean.blog.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface BlogService extends IService<Blog> {
    /**
     * 添加
     * @param map
     * @return
     */
    public Result addBlog(Map<String,Object> map);

    /**
     * 删除
     * @param blogId
     * @return
     */
    public Result deleteBlog(String blogId);

    /**
     * 修改
     * @param map
     * @return
     */
    public Result editBlog(Map<String,Object> map);

    /**
     * 查询
     * @param map
     * @return
     */
    public Result selectBlogById(Map<String,Object> map);

    /**
     * 查询列表
     * @param map
     * @return
     */
    public Result selectBlogList(Long page,Long size);

    /**
     * 查询
     * @param map
     * @return
     */
    public Result selectBlogListByParam(Map<String,Object> map);

    /**
     * 查询柱状图信息
     * @param map
     * @return
     */
    public Result<Object> selectBlogListOfEcharts();

    public Result selectRecycleBlogList(Long page, Long size);

    public Result deleteRecycleBlogById(String blogId);

    public Result deleteBatchIdList(Map<String, Object> param);

    Result<Object> selectArticleAndTagNum();

    Result<Object> classifyOfArticleNum();

    Result<Object> selectNewArticle();

    Result<Object> selectHoldArticle();

    Result<Object> selectTagCloud();

    Result<Object> selectBlogListIndex(Map<String,Integer> param);

    Result selectBlogReadNum();

    Result selectCarousel();

    Result selectCommentList();
}
