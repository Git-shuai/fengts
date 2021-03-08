package tian.web.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import tian.web.bean.user.RolePermission;

import java.util.List;
import java.util.Map;

public interface RolePermissionDao extends BaseMapper<RolePermission> {
    /**
     * 批量插入
     * @param roleId
     * @param apiIds
     * @return
     */
    int insertExp(@Param("roleId") String roleId, @Param("apiIds") List<Object> apiIds);

    /**
     * 查询该角色下的用户信息
     * @param roleId
     * @return
     */
    List<Map<String, Object>> selectRolePermissionListByRole(@Param("roleId") String roleId);
}