package tian.web.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tian.web.Result;
import tian.web.service.blog.BlogTagService;

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
@RequestMapping("/blogTag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    /**
     * 查询标签
     * @return Result
     */
    @GetMapping("/selectTag")
    public Result<Object> selectTag(){
        return blogTagService.selectTag();
    }

    /**
     * 查询标签下的博客
     * @return Result
     */
    @PostMapping("/selectBlogListByTag")
    public Result<Object> selectBlogListByTag(@RequestBody Map<String,Object> param){
        return blogTagService.selectBlogListByTag(param);
    }


}

