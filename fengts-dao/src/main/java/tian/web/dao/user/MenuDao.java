package tian.web.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.user.Menu;

import java.util.Map;

/**
 * @author FTS36
 */
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 查询菜单
     * @param page
     * @param map
     * @return IPage
     */
    public IPage<Map<String,Object>> selectMenuList(Page page);

    /**
     *
     * @param page
     * @param map
     * @return
     */
    public IPage<Map<String,Object>> selectMenuListBymap(Page page,@Param("map") Map<String,Object> map);

}