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
     * 修改博客
     * @param blogId 博客id
     * @return Result
     */
    @GetMapping("/deleteBlog/{blogId}")
    public Result deleteBlog( @PathVariable("blogId") String blogId){
        return blogService.deleteBlog(blogId);
    }

    @PostMapping("/selectBlogList/{page}/{size}")
    public Result selectBlogList( @PathVariable("page") Long page,@PathVariable("size") Long size){
        return blogService.selectBlogList(page,size);
    }

}

