package tian.web.service.user;

import tian.web.Result;
import tian.web.bean.user.Permission;

import java.util.Map;

/**
 * @author Administrator
 */
public interface PermissionService {
    /**
     * 增加后台API
     * @param permission
     * @return
     */
    public Result<Object> addPermission(Permission permission);

    /**
     * 删除后台API
     * @param perId
     * @return
     */
    public Result<Object> deletePermission(String perId);

    /**
     * 修改后台API
     * @param permission
     * @return
     */
    public Result<Object> editPermission(Permission permission);

    /**
     * 查询台API
     * @param param
     * @return
     */
    public Result<Object> selectPermission(Map<String,Object> param);

    /**
     * 条件查询
     * @param param
     * @return
     */
    public Result<Object> selectPermissionByParam(Map<String, Object> param);
}
