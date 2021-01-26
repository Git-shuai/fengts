package tian.web.controller.blog;


import org.apache.ibatis.annotations.Lang;
import org.springframework.web.bind.annotation.*;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.Tags;
import tian.web.enums.ResCode;
import tian.web.service.blog.TagsService;

import javax.annotation.Resource;
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
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagsService tagsService;

    /**
     * 添加标签控制器
     * @param tags 标签实体类
     * @return Result
     */
    @PostMapping("/addTags")
    public Result<Tags> addTags(@RequestBody Tags tags){
        if (tags==null){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return tagsService.addTags(tags);
    };

    /**
     * 删除标签
     * @param tagsId 标签的ID
     * @return Result
     */
    @GetMapping("/deleteTags/{tagsId}")
    public Result<Tags> deleteTags(@PathVariable("tagsId") String tagsId){
        return tagsService.deleteTags(tagsId);
    };

    /**
     * 修改标签
     * @param tags  标签实体类
     * @return Result
     */
    @PostMapping("/editTags")
    public Result<Tags> editTags(@RequestBody Tags tags){
        return tagsService.editTags(tags);
    };

    /**
     * 查询标签列表
     * @param param 当前页面
     * @return Result
     */
    @PostMapping("/selectTagsList")
    public Result selectTagsList(@RequestBody Map<String,Object> param){
        String page = StringUtils.getString(param.get("page"));
        String size = StringUtils.getString(param.get("size"));
        long pageL;
        long sizeL;
        try {
             pageL = Long.parseLong(page);
             sizeL = Long.parseLong(size);
        }catch (Exception e){
            return new Result(ResCode.ERROR_CODE,null);
        }

        return tagsService.selectTagsList(pageL,sizeL);
    };

    /**
     * 查询父标签接口
     * @return
     */
    @GetMapping("/parentTag")
    public Result selectParentTag(){
        return tagsService.selectParentTag();
    }
}

