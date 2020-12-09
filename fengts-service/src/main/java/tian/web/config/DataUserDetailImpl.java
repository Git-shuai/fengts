package tian.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tian.web.bean.user.Role;
import tian.web.dao.user.UserDao;
import tian.web.dao.user.UserRoleDao;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tian
 * @date 2020/12/4
 */
@Component
public class DataUserDetailImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //判断用用是否存在
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("username",username);
        List<tian.web.bean.user.User> users = userDao.selectByMap(userMap);
        if (users.isEmpty()){
            throw new UsernameNotFoundException("用户名不对");
        }
        tian.web.bean.user.User user=users.get(0);
        //取得角色
        userMap.put("userId",user.getUserId());
        List<Role> roles = userDao.getRoleByUsername(userMap);
        //并分配权限
        CopyOnWriteArrayList<GrantedAuthority> authorities = new CopyOnWriteArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(user.getUsername(),user.getPassword(),authorities);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
