package tian.web.service.user;

import tian.web.Result;

import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 7:53
 */
public interface RoleMenuService {
    /**
     * 批量添加
     * @param param
     * @return
     */
    public Result<Object> addExp(Map<String,Object> param);

    /**
     * 查询
     * @param param
     * @return
     */
    public Result<Object> selectRoleMenuList(Map<String,Object> param);
}
