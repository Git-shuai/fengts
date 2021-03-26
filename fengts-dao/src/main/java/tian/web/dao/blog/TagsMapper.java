package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.blog.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface TagsMapper extends BaseMapper<Tags> {
    /**
     * 联表查询标签的信息
     * @param page
     * @return
     */
    public IPage<Map<String,Object>> selectTagList(Page page);

    List<Map<String,Object>> selectTagCloud();
}
