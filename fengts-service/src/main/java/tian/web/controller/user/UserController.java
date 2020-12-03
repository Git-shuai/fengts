package tian.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tian.web.Result;
import tian.web.bean.user.User;
import tian.web.enums.ResCode;
import tian.web.service.user.UserService;

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
        Result result = new Result();
        Boolean insertStatus = userService.insertUser(user);
        if (insertStatus){
           result.setCode(ResCode.SUCCESS_CODE);
           result.setMessage("注册成功");
        }else {
            result.setCode(ResCode.ERROR_CODE);
            result.setMessage("注册失败");
        }
        return result;
    }
}
