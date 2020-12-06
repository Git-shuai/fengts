package tian.web.service.user.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tian.web.bean.user.Role;
import tian.web.bean.user.User;
import tian.web.dao.user.UserDao;
import tian.web.service.user.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 要实现UserDetailsService接口，这个接口是security提供的
 */
@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Resource
    private UserDao userDao;

    /**
     * 通过账号查找用户、角色的信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            List<Role> roles = userDao.getRoleByUsername(map);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }

            return new AuthUser(user.getUsername(), user.getPassword(), user.getDel(), authorities);
        }
    }
}
