package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.service.user.RoleMenuService;

import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 8:07
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;
    /**
     * 批量为角色添加菜单信息
     * @param param
     * @return
     */
    @PostMapping("/addExp")
    public Result<Object> addExp(@RequestBody Map<String,Object> param){
        return roleMenuService.addExp(param);
    }

    /**
     * 查询
     * @param param
     * @return
     */
    @PostMapping("/selectRoleMenuList")
    public Result<Object> selectRoleMenuList(@RequestBody Map<String,Object> param){
        return roleMenuService.selectRoleMenuList(param);
    }
}
