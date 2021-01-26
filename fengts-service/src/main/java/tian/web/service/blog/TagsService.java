package tian.web.service.blog;

import tian.web.Result;
import tian.web.bean.blog.Tags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface TagsService extends IService<Tags> {

    public Result<Tags> addTags(Tags tags);
    public Result<Tags> deleteTags(String tagsId);
    public Result<Tags> editTags(Tags tags);
    public Result selectTagsList(long page,long size);
    public Result selectParentTag();
}
