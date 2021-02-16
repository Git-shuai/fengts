package tian.web.service.user.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.user.Role;
import tian.web.dao.user.RoleDao;
import tian.web.service.user.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FTS
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Result<Object> addRole(Role role) {
        Result<Object> result = new Result<>();
        Boolean selectByName = this.selectByName(role.getRoleName());
        if (selectByName){
            result.setCode(-999);
            result.setMessage("已有该角色");
            return result;
        }
        int i = roleDao.insert(role);
        if (i<=0){
            result.setCode(-999);
            result.setMessage("添加失败");
            return result;
        }
        result.setCode(0);
        result.setData(role);
        result.setMessage("添加成功");
        return result;
    }

    @Override
    public Result<Object> deleteRole(String roleId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(roleId)){
            result.setCode(-999);
            result.setMessage("删除失败");
            return result;
        }
        int i = roleDao.deleteById(roleId);
        if (i<=0){
            result.setCode(-999);
            result.setMessage("删除失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("删除成功");
        return result;
    }

    @Override
    public Result<Object> editRole(Role role) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(role.getRoleId())){
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        int i = roleDao.updateById(role);
        if (i<=0){
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("更新成功");
        return result;
    }

    @Override
    public Result<Object> selectRole(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        long page;
        long size;
        String pageString = StringUtils.getString(param.get("page"));
        String sizeString = StringUtils.getString(param.get("size"));
        if (StringUtils.isEmpty(pageString) || StringUtils.isEmpty(sizeString)){
            page=1;
            size=8;
        }
        page=Long.parseLong(pageString);
        size=Long.parseLong(sizeString);
        Page<Role> rolePage = roleDao.selectPage(new Page<>(page, size), null);
        result.setCode(0);
        result.setData(rolePage);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Boolean selectByName(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_name",name);
        List<Role> roles = roleDao.selectByMap(map);
        return roles.size() > 0;
    }
}
