package tian.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tian.web.Result;
import tian.web.bean.user.User;
import tian.web.components.BCryptPasswordEncoderUtil;
import tian.web.dao.user.UserDao;
import tian.web.enums.ResCode;
import tian.web.service.user.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public Result insertUser(User user) {
        //判断用户是否存在
        Result result = new Result();
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
}
