package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.bean.user.Role;
import tian.web.service.user.RoleService;

import java.util.Map;

@RestController("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 添加角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    public Result<Object> addRole(Role role){
        return  roleService.addRole(role);
    }

    /**
     * 删除角色
     * @param roleId 角色Id
     * @return Result<Object>
     */
    public Result<Object> deleteRole(String roleId){
        return roleService.deleteRole(roleId);
    }

    /**
     * 修改角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    public Result<Object> editRole(Role role){
        return roleService.editRole(role);
    }

    /**
     * 分页查询
     * @param param 分页参数
     * @return Result<Object>
     */
    public Result<Object> selectRole(Map<String,Object> param){
        return roleService.selectRole(param);
    }

}
