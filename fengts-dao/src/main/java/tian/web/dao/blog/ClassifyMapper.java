package tian.web.dao.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tian.web.bean.blog.Classify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
public interface ClassifyMapper extends BaseMapper<Classify> {
    /**
     * 联表查询分类的信息
     * @param page
     * @return
     */
    public IPage<Map<String,Object>> selectClassifyList(Page page);

}
