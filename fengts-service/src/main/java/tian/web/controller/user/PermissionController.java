package tian.web.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tian.web.Result;
import tian.web.bean.user.Permission;
import tian.web.service.user.PermissionService;

import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    /**
     * 增加后台API
     * @param permission
     * @return
     */
    @PostMapping("/addPermission")
    public Result<Object> addPermission(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }

    /**
     * 删除后台API
     * @param perId
     * @return
     */
    @DeleteMapping("/deletePermission/{perId}")
    public Result<Object> deletePermission(@PathVariable("perId") String perId){
        return permissionService.deletePermission(perId);
    }

    /**
     * 修改后台API
     * @param permission
     * @return
     */
    @PutMapping("/editPermission")
    public Result<Object> editPermission(@RequestBody Permission permission){
        return  permissionService.editPermission(permission);
    }

    /**
     * 查询台API
     * @param param
     * @return
     */
    @PostMapping("/selectPermission")
    public Result<Object> selectPermission(@RequestBody Map<String,Object> param){
        return permissionService.selectPermission(param);
    }

    /**
     * 查询台API
     * @param param
     * @return
     */
    @PostMapping("/selectPermissionByParam")
    public Result<Object> selectPermissionByParam(@RequestBody Map<String,Object> param){
        return permissionService.selectPermissionByParam(param);
    }
}
