package tian.web.service.user;


import tian.web.Result;
import tian.web.bean.user.Menu;

import java.util.Map;

/**
 * @author FTS
 */
public interface MenuService {
    /**
     * 添加菜单
     * @param menu
     * @return
     */
    public Result<Object> addMenu(Menu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    public Result<Object> deleteMenu(String menuId);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    public Result<Object> editMenu(Menu menu);

    /**
     * 分页查询
     * @param param
     * @return
     */
    public Result<Object> selectMenuList(Map<String,Object> param);

    /**
     * 条件查询
     * @param param
     * @return
     */
    public Result<Object> selectMenuByParam(Map<String,Object> param);

    public Result<Object> selectMenuParentList();

    public Result<Object> selectMenuById(String menuId);
}
