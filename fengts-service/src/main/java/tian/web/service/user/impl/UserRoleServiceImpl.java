package tian.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.dao.user.UserRoleDao;
import tian.web.service.user.UserRoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FTS
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Result<Object> addUserRoleList(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        //判断角色ID是否为空
        if (StringUtils.isEmpty(param.get("roleId"))) {
            result.setCode(-999);
            result.setMessage("分配角色失败");
            return result;
        }
        //删除该角色下单用户
        String roleId = StringUtils.getString(param.get("roleId"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_id", roleId);
        int delete = userRoleDao.deleteByMap(map);
        if (delete < 0) {
            result.setCode(-999);
            result.setMessage("分配角色失败");
            return result;
        }
        //判断用户ID集合是否为空
        List<Object> userIds = (List<Object>) param.get("userIds");
        if (userIds.size()==0){
            result.setCode(0);
            result.setMessage("删除该角色下的用户成功");
            return result;
        }
        //添加该角色下单用户信息

        int insertExp = userRoleDao.insertExp(roleId,userIds);
        if (insertExp <= 0) {
            result.setCode(-999);
            result.setMessage("分配角色失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("分配角色成功");
        return result;
    }

    @Override
    public Result<Object> selectUserRoleList(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        String roleId = StringUtils.getString(param.get("roleId"));
        if (StringUtils.isEmpty(roleId)){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        List<Map<String,Object>> list=userRoleDao.selectUserRoleListByRole(roleId);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }
}
