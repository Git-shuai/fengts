package tian.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author tian
 * @date 2020/12/3
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().permitAll();
//
//
//        //没有权限自动跳到登录页面但是登录页面是框架自己写的
//        http.formLogin().loginPage("/toLogin");
//
//        //开启注销功能- https://blog.csdn.net/yjclsx/article/details/80349906
        http.csrf().disable();
//        //退出登录以后成功跳转的页面
//        http.logout().logoutSuccessUrl("/");
//        //记住我
//        http.rememberMe().rememberMeParameter("remember");
    }
}
