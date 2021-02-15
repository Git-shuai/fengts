package tian.web.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (userExits){
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
        if (i!=1){
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("用户注册失败");
        }
        result.setCode(ResCode.SUCCESS_CODE);
        result.setMessage("注册成功");
        //设置返回token
        String token = jwtTokenUtil.generateToken(user.getUsername());
        List<Menu> urls = this.getListMenuByUsername(user.getUsername());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("username",user.getUsername());
        map.put("menu",urls);
        map.put("auth","默认用户");
        result.setData(map);
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deleteUser(String userId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(userId)){
            result.setCode(-999);
            result.setMessage("不知道删除哪个用户");
            return result;
        }
        int deleteStatus = userDao.deleteById(userId);
        if (deleteStatus<=0){
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
        if (StringUtils.isEmpty(user)){
            result.setCode(-999);
            result.setMessage("不知道更新哪个用户");
            return result;
        }
        //设置更新时间
        user.setUpdateTime(new Date());
        //设置更新密码为密文
        if (!StringUtils.isEmpty(user.getPassword())){
            String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(newPassword);
        }
        int updateStatus = userDao.updateById(user);
        if (updateStatus<=0){
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
        if (StringUtils.isEmpty(pageString) || StringUtils.isEmpty(sizeString)){
            page=1;
            size=8;
        }
        page=Long.parseLong(pageString);
        size=Long.parseLong(sizeString);
        Page<User> userPage = userDao.selectPage(new Page<>(page, size), null);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(userPage);
        return result;
    }

    @Override
    public Result<Object> selectUserListByParam(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(param)){
            result.setCode(-999);
            result.setMessage("查询条件为空");
            return result;
        }
        //取得查询条件
        String username = StringUtils.getString(param.get("username"));

        String createTimeStart="";
        String createTimeEnd="";
        if (!StringUtils.isEmpty(param.get("createTime"))){
            List<String> createTime =(List<String>) param.get("createTime");
            createTimeStart=createTime.get(0);
            createTimeEnd=createTime.get(1);
        }
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(param.get("createTime"))){
            queryWrapper.like("username",username);
        }else {
            queryWrapper.like("username",username)
                    .between("create_time",createTimeStart,createTimeEnd);
        }
        Page<User> userPage = userDao.selectPage(new Page<>(page, size), queryWrapper);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(userPage);
        return result;
    }





    @Override
    public Boolean exitsUsername(User user){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        List<User> list = userDao.selectByMap(map);
        return list.size() > 0;
    }

    @Override
    public Boolean checkLogin(String username, String password) throws Exception {
        HashMap<String, Object> usernameMap = new HashMap<>();
        usernameMap.put("username",username);
        List<User> users = userDao.selectByMap(usernameMap);
        if (users.isEmpty()){
            throw  new Exception("账号不存在，请重新尝试！");
        }
        String dbPassword=users.get(0).getPassword();
        if (!bCryptPasswordEncoderUtil.matches(password,dbPassword)){
            //设置友好提示
            throw new Exception("密码不正确！");
        }
        return true;
    }

    @Override
    public User queryUserByUsername(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",username);
        List<User> users = userDao.selectByMap(map);
        if (!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<Permission> getListPerByUsername(String username) {
        return userDao.getListPerByUsername(username);
    }

    @Override
    public List<Menu> getListMenuByUsername(String username) {
        return userDao.getListMenuByUsername(username);
    }
}
