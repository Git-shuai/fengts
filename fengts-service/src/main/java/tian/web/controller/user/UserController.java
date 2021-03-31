package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tian.web.Result;
import tian.web.SendMail;
import tian.web.bean.user.User;
import tian.web.enums.ResCode;
import tian.web.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tian
 * @date 2020/12/3
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册API
     * @param user 用户类
     * @return Result
     */
    @PostMapping("/register")
    public Result registeredUser(@RequestBody User user){
        Result result = userService.insertUser(user);
        return result;
    }

    /**
     * 删除用户
     * @param userId 用户id
     * @return Result<Object>
     */
    @DeleteMapping("/deleteUser/{userId}")
    public Result<Object> deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }

    /**
     * 更新用户
     * @param user 用户实体类
     * @return Result<Object>
     */
    @PutMapping("/updateUser")
    public Result<Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 分页查询
     * @param param 入参
     * @return Result<Object>
     */
    @PostMapping("/selectUserList")
    public Result<Object> selectUserList(@RequestBody Map<String,Object> param){
        return userService.selectUserList(param);
    }

    /**
     * 根据用户名查询用户
     * @param username 查询条件
     * @return Result<Object>
     */
    @GetMapping("/queryUserByUsername/{username}")
    public Result<Object> queryUserByUsername(@PathVariable("username") String username){
        return  userService.selectUserByUserName(username);
    }

    /**
     * 根据条件查询
     * @param param 查询条件
     * @return Result<Object>
     */
    @PostMapping("/selectUserListByParam")
    public Result<Object> selectUserListByParam(@RequestBody Map<String,Object> param){
        return userService.selectUserListByParam(param);
    }


    /**
     * 用户查询API
     * @return  Result
     */
    @GetMapping("/admin/userlist")
    public Result userlist(){
        Result result=new Result();
        result.setMessage("用户列表");
        return result;
    }

    /**
     * 获取验证码API
     * @param user 用户实体类
     * @return Result<Object>
     */
    @PostMapping("/getCode")
    public Result<Object> getCode(@RequestBody User user){
        Result<Object> result = new Result<>();
        Boolean exitsUsername = userService.exitsUsername(user);
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        if (!exitsUsername){
            SendMail.sendText(user.getUsername(),code);
            result.setCode(0);
            result.setMessage("已发送");
            return result;
        }
        return userService.sendText(user.getUsername(),code);
    }

    /**
     * 更新用户名和密码
     * @param user 查询条件
     * @return Result<Object>
     */
    @PostMapping("/updateUserByUsernamePassword")
    public Result<Object> updateUserByUsernamePassword(@RequestBody User user){
        return userService.updateUserByUsernamePassword(user);
    }
}
