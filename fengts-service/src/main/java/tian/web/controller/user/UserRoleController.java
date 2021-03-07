package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.service.user.UserRoleService;

import java.util.Map;

/**
 * @author FTS
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 添加（更新）用户角色列表
     * @param param
     * @return
     */
    @PostMapping("/addUserRoleList")
    public Result<Object> addUserRoleList(@RequestBody Map<String,Object> param){
        return userRoleService.addUserRoleList(param);
    }

    /**
     * 查询用户角色列表
     * @param param
     * @return
     */
    @PostMapping("/selectUserRoleList")
    public Result<Object> selectUserRoleList(@RequestBody Map<String,Object> param){
        return userRoleService.selectUserRoleList(param);
    }

}
