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

    @PostMapping("/addBlog")
    public Result addBlog(@RequestBody Map<String,Object> params){
        return blogService.addBlog(params);
    }

    @GetMapping("/deleteBlog/{blogId}")
    public Result deleteBlog( @PathVariable("blogId") String blogId){
        return blogService.deleteBlog(blogId);
    }

}

