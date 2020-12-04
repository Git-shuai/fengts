package tian.web.service.user;

import tian.web.Result;
import tian.web.bean.user.User;

/**
 * @author tian
 * @date 2020/12/3
 */
public interface UserService {
    public Result insertUser(User user);

    public Boolean exitsUsername(User user);
}
