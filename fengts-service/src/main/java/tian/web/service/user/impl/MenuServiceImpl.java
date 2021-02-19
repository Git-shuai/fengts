package tian.web.service.user.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.user.Menu;
import tian.web.dao.user.MenuDao;
import tian.web.enums.ResCode;
import tian.web.service.user.MenuService;

import java.util.*;

/**
 * @author FTS
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Transactional
    @Override
    public Result<Object> addMenu(Menu menu) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(menu)) {
            result.setCode(-999);
            result.setMessage("添加失败");
            return result;
        }
        int i = menuDao.insert(menu);
        if (i <= 0) {
            result.setCode(-999);
            result.setMessage("添加失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("添加成功");
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deleteMenu(String menuId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(menuId)) {
            result.setCode(-999);
            result.setMessage("删除失败");
            return result;
        }
        //判断菜单是否有子级
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id",menuId);
        List<Menu> list = menuDao.selectByMap(map);
        if (list.size()!=0){
            result.setCode(-999);
            result.setMessage("该菜单还有下一级菜单");
            return result;
        }


        int i = menuDao.deleteById(menuId);
        if (i <= 0) {
            result.setCode(-999);
            result.setMessage("删除失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("删除成功");
        return result;
    }

    @Transactional
    @Override
    public Result<Object> editMenu(Menu menu) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(menu.getMenuId())) {
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        int i = menuDao.updateById(menu);
        if (i <= 0) {
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("更新成功");
        return result;
    }

    @Override
    public Result<Object> selectMenuList(Map<String, Object> param) {
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
        IPage<Map<String, Object>> mapPage = menuDao.selectMenuList(new Page<>(page, size));
        result.setCode(0);
        result.setData(mapPage);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<Object> selectMenuByParam(Map<String, Object> param) {
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
        //查询参数处理
        HashMap<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(param.get("parentId"))) {
            map.put("parentId", param.get("parentId"));
        }
        if (!StringUtils.isEmpty(param.get("menuItemName"))) {
            map.put("menuItemName", "%" + param.get("menuItemName") + "%");
        }
        if (!StringUtils.isEmpty(param.get("menuName"))) {
            map.put("menuName", "%" + param.get("menuName") + "%");
        }
        if (!StringUtils.isEmpty(param.get("menuUrl"))) {
            map.put("menuUrl", "%" + param.get("menuUrl") + "%");
        }
        IPage<Map<String, Object>> mapPage = menuDao.selectMenuListBymap(new Page<>(page, size), map);
        result.setCode(0);
        result.setData(mapPage);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<Object> selectMenuParentList() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id", null);
        List<Menu> list = menuDao.selectByMap(map);
        return new Result<>(ResCode.SUCCESS_CODE, list);
    }

    @Override
    public Result<Object> selectMenuById(String menuId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(menuId)){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        Menu menu = menuDao.selectById(menuId);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(menu);
        return result;
    }
}
