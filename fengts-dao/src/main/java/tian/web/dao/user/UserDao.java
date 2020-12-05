package tian.web.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tian.web.bean.user.Role;
import tian.web.bean.user.User;

import java.util.List;
import java.util.Map;

/**
 * @author FTS36
 */
public interface UserDao extends BaseMapper<User> {

    public List<Role> getRoleByUsername(Map<String,Object> params);
}