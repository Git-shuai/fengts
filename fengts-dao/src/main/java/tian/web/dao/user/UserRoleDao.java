package tian.web.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.user.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @author FTS36
 */
public interface UserRoleDao extends BaseMapper<UserRole> {

    /**
     * 批量插入
     * @param roleId
     * @param userIds
     * @return
     */
    int insertExp(@Param("roleId") String roleId,@Param("userIds") List<Object> userIds);

    /**
     * 查询该角色下的用户信息
     * @param roleId
     * @return
     */
    List<Map<String, Object>> selectUserRoleListByRole(@Param("roleId") String roleId);
}