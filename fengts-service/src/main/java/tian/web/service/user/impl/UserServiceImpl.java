package tian.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tian.web.bean.user.User;
import tian.web.dao.user.UserDao;
import tian.web.service.user.UserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tian
 * @date 2020/12/3
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean insertUser(User user) {
        //利用 spring Security 自带的加密
        String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(newPassword);
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        int i = userDao.insert(user);
        return i == 1;
    }
}
