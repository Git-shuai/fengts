package tian.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.dao.user.RoleMenuDao;
import tian.web.service.user.RoleMenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 7:55
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Result<Object> addExp(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        //判断角色ID是否为空
        if (StringUtils.isEmpty(param.get("roleId"))) {
            result.setCode(-999);
            result.setMessage("分配菜单失败");
            return result;
        }
        //删除该角色下单菜单
        String roleId = StringUtils.getString(param.get("roleId"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_id", roleId);
        int delete = roleMenuDao.deleteByMap(map);
        if (delete < 0) {
            result.setCode(-999);
            result.setMessage("分配菜单失败");
            return result;
        }
        //判断用户ID集合是否为空
        List<Object> menuIds = (List<Object>) param.get("menuIds");
        if (menuIds.size()==0){
            result.setCode(0);
            result.setMessage("删除该角色下的菜单成功");
            return result;
        }

        int insertExp = roleMenuDao.insertExp(roleId,menuIds);
        if (insertExp <= 0) {
            result.setCode(-999);
            result.setMessage("分配菜单失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("分配菜单成功");
        return result;
    }

    @Override
    public Result<Object> selectRoleMenuList(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        String roleId = StringUtils.getString(param.get("roleId"));
        if (StringUtils.isEmpty(roleId)){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        List<Map<String,Object>> list=roleMenuDao.selectUserRoleListByRole(roleId);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }
}
