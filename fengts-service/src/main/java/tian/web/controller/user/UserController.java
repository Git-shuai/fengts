package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public Result registeredUser(@RequestBody User user){
        Result result = userService.insertUser(user);
        return result;
    }

//    @PostMapping("/login")
//    public Result login(@RequestBody User user){
//        Result result =new Result();
//        //判断用户是否存在
//        Boolean exitsUsername = userService.exitsUsername(user);
//        if (!exitsUsername){
//           result.setCode(ResCode.ERROR_CODE);
//           result.setMessage("用户名不存在");
//        }
//        //登录
//        result.setCode(ResCode.SUCCESS_CODE);
//        result.setMessage("登录成功");
//        return result;
//    }

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