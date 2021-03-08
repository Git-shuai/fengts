package tian.web.service.user;

import tian.web.Result;

import java.util.Map;

/**
 * @author FTS
 * @date 2021/3/8 9:52
 */
public interface RolePermissionService {
    /**
     * 批量插入API
     * @param param
     * @return
     */
    public Result<Object> addAPIExp(Map<String,Object> param);

    /**
     * 查询
     * @param param
     * @return
     */
    public Result<Object> selectAPIListExp(Map<String,Object> param);
}
