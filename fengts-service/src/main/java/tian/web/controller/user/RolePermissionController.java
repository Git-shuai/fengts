package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.service.user.RolePermissionService;

import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 10:06
 */
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 批量插入API
     * @param param
     * @return
     */
    @PostMapping("/addAPIExp")
    public Result<Object> addAPIExp(@RequestBody Map<String,Object> param){
        return rolePermissionService.addAPIExp(param);
    }

    /**
     * 查询
     * @param param
     * @return
     */
    @PostMapping("/selectAPIListExp")
    public Result<Object> selectAPIListExp(@RequestBody Map<String,Object> param){
        return rolePermissionService.selectAPIListExp(param);
    }
}
