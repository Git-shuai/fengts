package tian.web.service.user;

import tian.web.Result;
import tian.web.bean.user.Menu;
import tian.web.bean.user.Permission;
import tian.web.bean.user.User;

import java.util.List;
import java.util.Map;

/**
 * @author tian
 * @date 2020/12/3
 */
public interface UserService {
    /**
     * 添加用户
     * @param user 用户实体类
     * @return Result<Object>
     */
    public Result<Object> insertUser(User user);

    /**
     * 删除用户
     * @param userId 用户id
     * @return Result<Object>
     */
    public Result<Object> deleteUser(String userId);

    /**
     * 更新用户
     * @param user 用户实体类
     * @return Result<Object>
     */
    public Result<Object> updateUser(User user);

    /**
     * 分页查询
     * @param param 入参
     * @return Result<Object>
     */
    public Result<Object> selectUserList(Map<String,Object> param);

    /**
     * 根据条件查询
     * @param param 查询条件
     * @return Result<Object>
     */
    public Result<Object> selectUserListByParam(Map<String,Object> param);

    /**
     * 根据用户名查询用户
     * @param userName 查询条件
     * @return Result<Object>
     */
    public Result<Object> selectUserByUserName(String userName);

    /**
     * 更新用户名和密码
     * @param user 查询条件
     * @return Result<Object>
     */
    public Result<Object> updateUserByUsernamePassword(User user);


    public Boolean exitsUsername(User user);

    public Boolean checkLogin(String username,String password) throws Exception;

    public User queryUserByUsername(String username);

    public List<Permission> getListPerByUsername(String username);

    public List<Map<String,Object>> getListMenuByUsername(String username);
}
