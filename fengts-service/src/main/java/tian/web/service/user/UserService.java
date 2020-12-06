package tian.web.service.user;

import tian.web.Result;
import tian.web.bean.user.Permission;
import tian.web.bean.user.User;

import java.util.List;

/**
 * @author tian
 * @date 2020/12/3
 */
public interface UserService {
    public Result insertUser(User user);

    public Boolean exitsUsername(User user);

    public Boolean checkLogin(String username,String password) throws Exception;

    public User queryUserByUsername(String username);

    public List<Permission> getListPerByUsername(String username);
}
