package tian.web.service.user;

import tian.web.Result;
import tian.web.bean.user.Role;

import java.util.Map;

/**
 * @author FTS
 */
public interface RoleService {
    /**
     * 添加角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    public Result<Object> addRole(Role role);

    /**
     * 删除角色
     * @param roleId 角色Id
     * @return Result<Object>
     */
    public Result<Object> deleteRole(String roleId);

    /**
     * 修改角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    public Result<Object> editRole(Role role);

    /**
     * 分页查询
     * @param param 分页参数
     * @return Result<Object>
     */
    public Result<Object> selectRole(Map<String,Object> param);

    /**
     * 查询角色是否存在
     * @param name 角色名
     * @return Boolean
     */
    public Boolean selectByName(String name);
}
