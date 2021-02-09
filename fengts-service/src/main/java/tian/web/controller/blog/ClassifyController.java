package tian.web.controller.blog;


import org.springframework.web.bind.annotation.*;

import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.Classify;
import tian.web.bean.blog.Tags;
import tian.web.enums.ResCode;
import tian.web.service.blog.ClassifyService;

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
@RequestMapping("/classify")
public class ClassifyController {
    @Resource
    private ClassifyService classifyService;

    @PostMapping("/addClassify")
    public Result<Classify> addClassify(@RequestBody Classify classify){
        Result<Classify> result = new Result<>();
        if (classify==null){
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("添加失败");
            return result;
        }
        return classifyService.addClassify(classify);
    }

    /**
     * 删除分类
     * @param classifyId 分类的ID
     * @return Result
     */
    @GetMapping("/deleteClassify/{classifyId}")
    public Result<Classify> deleteClassify(@PathVariable("classifyId") String classifyId){
        return classifyService.deleteClassifyById(classifyId);
    };

    /**
     * 修改分类
     * @param classify  分类实体类
     * @return Result
     */
    @PostMapping("/editClassify")
    public Result<Classify> editClassify(@RequestBody Classify classify){
        return classifyService.editClassify(classify);
    };

    /**
     * 查询分类列表
     * @param param 当前页面
     * @return Result
     */
    @PostMapping("/selectClassifyList")
    public Result selectClassifyList(@RequestBody Map<String,Object> param){
        return classifyService.selectClassifyList(param);
    }

    /**
     * 查询分类根据name
     * @param param 当前页面
     * @return Result
     */
    @PostMapping("/selectClassifyByName")
    public Result selectClassifyByName(@RequestBody Map<String,Object> param){
        return classifyService.selectClassifyByName(param);
    }
    /**
     * 查询分类列表
     * @param param 当前页面
     * @return Result
     */
    @PostMapping("/selectClassify")
    public Result selectClassifyList(){
        return classifyService.selectClassifyList();
    }

    /**
     * 查询父分类接口
     * @return
     */
    @GetMapping("/selectParentClassify")
    public Result selectParentClassify(){
        return classifyService.selectParentClassify();
    }

}

