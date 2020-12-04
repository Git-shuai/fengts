package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tian.web.bean.user.User;
import tian.web.service.user.UserService;
import tian.web.service.user.impl.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @author tian
 * @date 2020/12/4
 */
public class Test {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @org.junit.Test
    public void test1(){
        User user = new User();
        user.setUsername("冯天帅");
        Boolean aBoolean = userService.exitsUsername(user);
        System.out.println(aBoolean);
    }

    @org.junit.Test
    public void test(){
        System.out.println(String.valueOf((int)((Math.random() * 9 + 1) * 100000)));
    }
}
