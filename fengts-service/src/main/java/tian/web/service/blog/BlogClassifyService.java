package tian.web.service.blog;

import tian.web.Result;
import tian.web.bean.blog.BlogClassify;
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
public interface BlogClassifyService extends IService<BlogClassify> {
    /**
     * 查询分类
     * @return Result
     */
    public Result<Object> selectClassify();

    /**
     * 查询分类下的博客
     * @return Result
     */
    public Result<Object> selectBlogListByClassify(Map<String,Object> param);
}
