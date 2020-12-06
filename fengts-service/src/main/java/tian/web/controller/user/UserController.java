package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.bean.user.User;
import tian.web.enums.ResCode;
import tian.web.service.user.UserService;

import java.util.HashMap;

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
        if (!exitsUsername){
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("用户名不存在");
        }
        result.setCode(ResCode.SUCCESS_CODE);
        result.setMessage("验证码已发送");
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",String.valueOf((int)((Math.random() * 9 + 1) * 100000)));
        result.setData(map);
        return result;
    }
}
