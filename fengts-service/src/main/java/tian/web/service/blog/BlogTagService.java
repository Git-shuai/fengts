package tian.web.service.blog;

import tian.web.Result;
import tian.web.bean.blog.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface BlogTagService extends IService<BlogTag> {
    /**
     * 查询标签
     * @return Result
     */
    public Result<Object> selectTag();

    /**
     * 查询标签下的博客
     * @return Result
     */
    public Result<Object> selectBlogListByTag(Map<String,Object> param);

}
