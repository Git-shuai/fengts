package tian.web.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import tian.web.Result;
import tian.web.bean.blog.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author FTS
 * @date 2021/1/4
 */
public interface BlogService {

    /**
     * 添加博客
     *  @param params
     * @return Result<Blog>
     */
    public Result<Blog> addBlog(Map<String,Object> params);

    /**
     * 删除博客
     *  @param blogId
     * @return Result<Blog>
     */
    public Result<Blog> deleteBlog(String blogId);


    /**
     * 修改博客
     * @param params
     * @return Result<Blog>
     */
    public Result<Blog> modifyBlog(Map<String,Object> params);


    /**
     * 查询一个博客内容
     * @param blogId
     * @return Result<Blog>
     */
    public Result<Blog> selectBlogById(String blogId);

    /**
     * 查询博客列表内容
     * @param limit
     * @param off
     * @return Result<List<Blog>>
     */
    public Result<List<Blog>> selectBlogList(String off,String limit);


}
