package tian.web.service.blog;

import tian.web.Result;
import tian.web.bean.blog.Classify;
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
public interface ClassifyService extends IService<Classify> {
    public Result<Classify> addClassify(Classify classify);
    public Result<Classify> deleteClassifyById(String classifyId);
    public Result<Classify> editClassify(Classify classify);
    public Result selectClassifyList(Map<String,Object> map);
    public Result selectParentClassify();
}
