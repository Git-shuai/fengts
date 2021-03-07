package tian.web.service.user;

import tian.web.Result;

import java.util.Map;

/**
 * @author FTS
 */
public interface UserRoleService {

    /**
     * 添加（更新）用户角色列表
     * @param param
     * @return
     */
    public Result<Object> addUserRoleList(Map<String,Object> param);

    /**
     * 查询用户角色列表
     * @param param
     * @return
     */
    public Result<Object> selectUserRoleList(Map<String,Object> param);


}
