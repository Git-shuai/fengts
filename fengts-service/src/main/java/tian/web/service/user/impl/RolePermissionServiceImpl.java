package tian.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.dao.user.RolePermissionDao;
import tian.web.service.user.RolePermissionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 9:54
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public Result<Object> addAPIExp(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        //判断角色ID是否为空
        if (StringUtils.isEmpty(param.get("roleId"))) {
            result.setCode(-999);
            result.setMessage("分配API失败");
            return result;
        }
        //删除该角色下单菜单
        String roleId = StringUtils.getString(param.get("roleId"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_id", roleId);
        int delete = rolePermissionDao.deleteByMap(map);
        if (delete < 0) {
            result.setCode(-999);
            result.setMessage("分配API失败");
            return result;
        }
        //判断API ID集合是否为空
        List<Object> apiIds = (List<Object>) param.get("apiIds");
        if (apiIds.size()==0){
            result.setCode(0);
            result.setMessage("删除该角色下的API成功");
            return result;
        }

        int insertExp = rolePermissionDao.insertExp(roleId,apiIds);
        if (insertExp <= 0) {
            result.setCode(-999);
            result.setMessage("分配API失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("分配API成功");
        return result;
    }

    @Override
    public Result<Object> selectAPIListExp(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        String roleId = StringUtils.getString(param.get("roleId"));
        if (StringUtils.isEmpty(roleId)){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        List<Map<String,Object>> list=rolePermissionDao.selectRolePermissionListByRole(roleId);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }
}
