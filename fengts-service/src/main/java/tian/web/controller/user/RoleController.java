package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tian.web.Result;
import tian.web.bean.user.Role;
import tian.web.service.user.RoleService;

import java.util.Map;

/**
 * @author FTS
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 添加角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    @PostMapping("/addRole")
    public Result<Object> addRole(@RequestBody Role role){
        return  roleService.addRole(role);
    }

    /**
     * 删除角色
     * @param roleId 角色Id
     * @return Result<Object>
     */
    @DeleteMapping("/deleteRole/{roleId}")
    public Result<Object> deleteRole(@PathVariable("roleId") String roleId){
        return roleService.deleteRole(roleId);
    }

    /**
     * 修改角色
     * @param role 角色实体类
     * @return Result<Object>
     */
    @PutMapping("/editRole")
    public Result<Object> editRole(@RequestBody Role role){
        return roleService.editRole(role);
    }

    /**
     * 分页查询
     * @param param 分页参数
     * @return Result<Object>
     */
    @PostMapping("/selectRole")
    public Result<Object> selectRole(@RequestBody Map<String,Object> param){
        return roleService.selectRole(param);
    }

}
