package tian.web.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.user.Permission;
import tian.web.dao.user.PermissionDao;
import tian.web.service.user.PermissionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Transactional
    @Override
    public Result<Object> addPermission(Permission permission) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(permission)){
            result.setMessage("添加失败");
            result.setCode(-999);
            return result;
        }
        //判断API是否存在
        if (!StringUtils.isEmpty(permission.getPerApiUrl())){
            HashMap<String, Object> map = new HashMap<>();
            map.put("per_api_url",permission.getPerApiUrl());
            map.put("per_method",permission.getPerMethod());
            List<Permission> list = permissionDao.selectByMap(map);
            if (list.size()>0){
                result.setMessage("已有该API");
                result.setCode(-999);
                return result;
            }
        }
        int insert = permissionDao.insert(permission);
        if (insert<=0){
            result.setMessage("添加失败");
            result.setCode(-999);
            return result;
        }
        result.setMessage("添加成功");
        result.setCode(0);
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deletePermission(String perId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(perId)){
            result.setMessage("删除失败");
            result.setCode(-999);
            return result;
        }
        int deleteById = permissionDao.deleteById(perId);
        if (deleteById<=0){
            result.setMessage("删除失败");
            result.setCode(-999);
            return result;
        }
        result.setMessage("删除成功");
        result.setCode(0);
        return result;
    }

    @Transactional
    @Override
    public Result<Object> editPermission(Permission permission) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(permission.getPerId())){
            result.setMessage("更新失败");
            result.setCode(-999);
            return result;
        }
        int updateById = permissionDao.updateById(permission);
        if (updateById<=0){
            result.setMessage("更新失败");
            result.setCode(-999);
            return result;
        }
        result.setMessage("更新成功");
        result.setCode(0);
        return result;
    }



    @Override
    public Result<Object> selectPermission(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        long page;
        long size;
        String pageString = StringUtils.getString(param.get("page"));
        String sizeString = StringUtils.getString(param.get("size"));
        if (StringUtils.isEmpty(pageString) || StringUtils.isEmpty(sizeString)) {
            page = 1;
            size = 8;
        }
        page = Long.parseLong(pageString);
        size = Long.parseLong(sizeString);
        Page<Map<String, Object>> mapPage = permissionDao.selectMapsPage(new Page<>(page, size), null);
        result.setMessage("查询成功");
        result.setData(mapPage);
        result.setCode(0);
        return result;
    }

    @Override
    public Result<Object> selectPermissionByParam(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        long page;
        long size;
        String pageString = StringUtils.getString(param.get("page"));
        String sizeString = StringUtils.getString(param.get("size"));
        if (StringUtils.isEmpty(pageString) || StringUtils.isEmpty(sizeString)) {
            page = 1;
            size = 8;
        }
        page = Long.parseLong(pageString);
        size = Long.parseLong(sizeString);

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("per_name",param.get("perName"))
                    .like("per_method",param.get("perMethod"));
        Page<Map<String, Object>> mapPage = permissionDao.selectMapsPage(new Page<>(page, size), queryWrapper);
        result.setMessage("查询成功");
        result.setData(mapPage);
        result.setCode(0);
        return result;
    }
}
