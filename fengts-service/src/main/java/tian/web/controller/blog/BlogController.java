package tian.web.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tian.web.Result;
import tian.web.service.blog.BlogService;

import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 添加博客
     * @param params 入参
     * @return Result
     */
    @PostMapping("/addBlog")
    public Result addBlog(@RequestBody Map<String,Object> params){
        return blogService.addBlog(params);
    }

    /**
     * 删除博客
     * @param blogId 博客id
     * @return Result
     */
    @GetMapping("/deleteBlog/{blogId}")
    public Result deleteBlog( @PathVariable("blogId") String blogId){
        return blogService.deleteBlog(blogId);
    }

    /**
     * 批量删除博客
     * @param blogId 博客id
     * @return Result
     */
    @PostMapping("/deleteBatchIdList")
    public Result deleteBatchIdList(@RequestBody Map<String,Object> param){
        return blogService.deleteBatchIdList(param);
    }


    /**
     * 回收站删除博客
     * @param blogId 博客id
     * @return Result
     */
    @GetMapping("/deleteRecycleBlogById/{blogId}")
    public Result deleteRecycleBlogById( @PathVariable("blogId") String blogId){
        return blogService.deleteRecycleBlogById(blogId);
    }

    /**
     * 修改博客
     * @param param
     * @return
     */
    @PostMapping("editBlog")
    public Result editBlog(@RequestBody Map<String,Object> param){
        Result result = blogService.editBlog(param);
        return  result;

    }

    /**
     * 查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectBlogList/{page}/{size}")
    public Result selectBlogList( @PathVariable("page") Long page,@PathVariable("size") Long size){
        return blogService.selectBlogList(page,size);
    }

    /**
     * 回收站查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectRecycleBlogList/{page}/{size}")
    public Result selectRecycleBlogList( @PathVariable("page") Long page,@PathVariable("size") Long size){
        return blogService.selectRecycleBlogList(page,size);
    }
    /**
     * 分页查询
     * @param param
     * @return
     */
    @PostMapping("/selectBlog")
    public Result selectBlog(@RequestBody Map<String,Object> param){
        return blogService.selectBlogById(param);
    }

    @PostMapping("/selectBlogListByParam")
    public Result selectBlogListByParam(@RequestBody Map<String,Object> param){
        return  blogService.selectBlogListByParam(param);
    }

    /**
     * 用户柱状图
     * @return
     */
    @GetMapping("/selectBlogListOfEcharts")
    public Result<Object> selectBlogListOfEcharts(){
        return  blogService.selectBlogListOfEcharts();
    }


}

