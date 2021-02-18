package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tian.web.Result;
import tian.web.bean.user.Menu;
import tian.web.service.user.MenuService;

import java.util.Map;

/**
 * @author FTS
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @PostMapping("/addMenu")
    public Result<Object> addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/deleteMenu/{menuId}")
    public Result<Object> deleteMenu(@PathVariable("menuId") String menuId){
        return menuService.deleteMenu(menuId);
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PutMapping("/editMenu")
    public Result<Object> editMenu(@RequestBody Menu menu){
        return menuService.editMenu(menu);
    }

    /**
     * 条件查询
     * @param param
     * @return
     */
    @PostMapping("/selectMenuByParam")
    public Result<Object> selectMenuByParam(@RequestBody Map<String,Object> param){
        return menuService.selectMenuByParam(param);
    }
    /**
     * 分页查询
     * @param param
     * @return
     */
    @PostMapping("/selectMenuList")
    public Result<Object> selectMenuList(@RequestBody Map<String,Object> param){
       return menuService.selectMenuList(param);
    }

    /**
     * 分页无父类的菜单
     * @return
     */
    @GetMapping("/selectMenuParentList")
    public Result<Object> selectMenuParentList(){
        return menuService.selectMenuParentList();
    }

    /**
     * 根据ID查询
     * @return
     */
    @GetMapping("/selectMenuById/{menuId}")
    public Result<Object> selectMenuById(@PathVariable("menuId") String menuId){
        return menuService.selectMenuById(menuId);
    }

}
