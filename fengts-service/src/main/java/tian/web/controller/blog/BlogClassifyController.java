package tian.web.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tian.web.Result;
import tian.web.service.blog.BlogClassifyService;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/blogClassify")
public class BlogClassifyController {

    @Autowired
    private BlogClassifyService blogClassifyService;

    /**
     * 查询分类
     *
     * @return Result
     */
    @GetMapping("/selectClassify")
    public Result<Object> selectClassify(){
        return blogClassifyService.selectClassify();
    }

    /**
     * 查询分类下的博客
     *
     * @return Result
     */
    @PostMapping("/selectBlogListByClassify")
    public Result<Object> selectBlogListByClassify(@RequestBody Map<String, Object> param){
        return blogClassifyService.selectBlogListByClassify(param);
    }
}

