package tian.web.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.SendMail;
import tian.web.StringUtils;
import tian.web.bean.user.Menu;
import tian.web.bean.user.Permission;
import tian.web.bean.user.User;
import tian.web.components.BCryptPasswordEncoderUtil;
import tian.web.components.JwtTokenUtil;
import tian.web.dao.user.UserDao;
import tian.web.enums.ResCode;
import tian.web.service.user.UserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author tian
 * @date 2020/12/3
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    @Resource
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Transactional
    @Override
    public Result<Object> insertUser(User user) {
        //判断用户是否存在
        Result<Object> result = new Result<>();
        Boolean userExits = this.exitsUsername(user);
        if (userExits) {
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("用户名已存在");
            return result;
        }
        //利用 spring Security 自带的加密
        String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(newPassword);
        user.setDel(0);
        user.setVersion(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        int i = userDao.insert(user);
        if (i != 1) {
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("用户注册失败");
        }
        result.setCode(ResCode.SUCCESS_CODE);
        result.setMessage("注册成功");
        //设置返回token
        String token = jwtTokenUtil.generateToken(user.getUsername());
        List<Map<String, Object>> urls = this.getListMenuByUsername(user.getUsername());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("username", user.getUsername());
        map.put("menu", urls);
        map.put("auth", "默认用户");
        result.setData(map);
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deleteUser(String userId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(userId)) {
            result.setCode(-999);
            result.setMessage("不知道删除哪个用户");
            return result;
        }
        int deleteStatus = userDao.deleteById(userId);
        if (deleteStatus <= 0) {
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
    public Result<Object> updateUser(User user) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(user)) {
            result.setCode(-999);
            result.setMessage("不知道更新哪个用户");
            return result;
        }
        //设置更新时间
        user.setUpdateTime(new Date());
        //设置更新密码为密文
        if (!StringUtils.isEmpty(user.getPassword())) {
            String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(newPassword);
        }
        int updateStatus = userDao.updateById(user);
        if (updateStatus <= 0) {
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("更新成功");
        result.setData(user);
        return result;
    }

    @Override
    public Result<Object> selectUserList(Map<String, Object> param) {
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
        Page<User> userPage = userDao.selectPage(new Page<>(page, size), null);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(userPage);
        return result;
    }

    @Override
    public Result<Object> selectUserListByParam(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(param)) {
            result.setCode(-999);
            result.setMessage("查询条件为空");
            return result;
        }
        //取得查询条件
        String username = StringUtils.getString(param.get("username"));

        String createTimeStart = "";
        String createTimeEnd = "";
        if (!StringUtils.isEmpty(param.get("createTime"))) {
            List<String> createTime = (List<String>) param.get("createTime");
            createTimeStart = createTime.get(0);
            createTimeEnd = createTime.get(1);
        }
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(param.get("createTime"))) {
            queryWrapper.like("username", username);
        } else {
            queryWrapper.like("username", username)
                    .between("create_time", createTimeStart, createTimeEnd);
        }
        Page<User> userPage = userDao.selectPage(new Page<>(page, size), queryWrapper);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(userPage);
        return result;
    }

    @Override
    public Result<Object> selectUserByUserName(String username) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(username)) {
            result.setCode(-999);
            result.setMessage("没有该用户");
            return result;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> users = userDao.selectByMap(map);
        if (users.size() <= 0) {
            result.setCode(-999);
            result.setMessage("没有该用户");
            return result;
        }
        result.setCode(0);
        result.setData(users.get(0));
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<Object> updateUserByUsernamePassword(User user) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(user)) {
            result.setCode(-999);
            result.setMessage("不知道更新哪个用户");
            return result;
        }
        //设置更新时间
        user.setUpdateTime(new Date());
        //设置更新密码为密文
        if (!StringUtils.isEmpty(user.getPassword())) {
            String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(newPassword);
        }
        String token = "";
        if (!StringUtils.isEmpty(user.getUsername())) {
            token = jwtTokenUtil.generateToken(user.getUsername());
        }
        int updateStatus = userDao.updateById(user);
        if (updateStatus <= 0) {
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("更新成功");
        result.setData(token);
        return result;
    }


    @Override
    public Boolean exitsUsername(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        List<User> list = userDao.selectByMap(map);
        return list.size() > 0;
    }

    @Override
    public Boolean checkLogin(String username, String password, String code) throws Exception {
        HashMap<String, Object> usernameMap = new HashMap<>();
        usernameMap.put("username", username);
        List<User> users = userDao.selectByMap(usernameMap);
        if (users.isEmpty()) {
            throw new Exception("账号不存在，请重新尝试！");
        }
        String dbPassword = users.get(0).getPassword();
        if (!bCryptPasswordEncoderUtil.matches(password, dbPassword)) {
            //设置友好提示
            throw new Exception("密码不正确！");
        }
        String DBCode = users.get(0).getCode();
        if (!code.equals(DBCode)) {
            throw new Exception("验证码错误！");
        }
        return true;
    }

    @Override
    public User queryUserByUsername(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> users = userDao.selectByMap(map);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<Permission> getListPerByUsername(String username) {
        return userDao.getListPerByUsername(username);
    }

    @Override
    public List<Map<String, Object>> getListMenuByUsername(String username) {
        List<Map<String, Object>> menu = userDao.getListMenuByUsername(username);
        //取出父级
        List<Map<String, Object>> parentList = getParentNodeList(menu, "parentId");
        //取出子级
        List<Map<String, Object>> childList = getNodeList(menu, "parentId");
        //向父级集合中塞如子级
        return getParentMakeChildList(parentList, childList);

    }

    /**
     * @param parentNode
     * @param childNode
     * @return
     */
    public List<Map<String, Object>> getParentMakeChildList(List<Map<String, Object>> parentNode, List<Map<String, Object>> childNode) {

        if (StringUtils.isEmpty(parentNode) || StringUtils.isEmpty(childNode)) {
            return parentNode;
        }
        for (Map<String, Object> parentMap : parentNode) {
            ArrayList<Map<String, Object>> childList = new ArrayList<>();
            for (Map<String, Object> childMap : childNode) {
                String menuId = StringUtils.getString(parentMap.get("menuId"));
                String parentId = StringUtils.getString(childMap.get("parentId"));
                if (menuId.equals(parentId)) {
                    childList.add(childMap);
                }
            }
            parentMap.put("child", childList);
        }
        return parentNode;
    }

    /**
     * @param parentNode
     * @param nodeName
     * @return
     */
    public List<Map<String, Object>> getParentNodeList(List<Map<String, Object>> parentNode, String nodeName) {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        if (StringUtils.isEmpty(parentNode)) {
            return result;
        }
        for (Map<String, Object> map : parentNode) {
            if (StringUtils.isEmpty(StringUtils.getString(map.get(nodeName)))) {
                result.add(map);
            }
        }
        return result;
    }

    /**
     * @param node
     * @param nodeName
     * @return
     */
    public List<Map<String, Object>> getNodeList(List<Map<String, Object>> node, String nodeName) {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        if (StringUtils.isEmpty(node)) {
            return result;
        }
        for (Map<String, Object> map : node) {
            if (!StringUtils.isEmpty(StringUtils.getString(map.get(nodeName)))) {
                result.add(map);
            }
        }
        return result;
    }

    @Transactional
    @Override
    public Result<Object> sendText(String username, String code) {
        Result<Object> result = new Result<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",username);
        List<User> users = userDao.selectByMap(map);
        if (users.isEmpty()){
            result.setCode(-99);
            result.setMessage("发送失败");
            return result;
        }
        User user = new User();
        user.setUserId(users.get(0).getUserId());
        user.setCode(code);
        int i = userDao.updateById(user);
        if (i<=0){
            result.setCode(-99);
            result.setMessage("发送失败");
            return result;
        }
        SendMail.sendText(username,code);
        result.setCode(0);
        result.setMessage("发送成功");
        return result;
    }
}
