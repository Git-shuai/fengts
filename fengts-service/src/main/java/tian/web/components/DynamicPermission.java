package tian.web.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import tian.web.bean.user.Permission;
import tian.web.config.auth.MyaccessDeniedException;
import tian.web.dao.user.UserDao;
import tian.web.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author FTS36
 */
@Component
public class DynamicPermission {

    @Autowired
    private UserService userService;

    /**
     * 判断有访问API的权限
     * @param request
     * @param authentication
     * @return
//     * @throws MyaccessDeniedException
     */
    public boolean checkPermisstion(HttpServletRequest request,
                                    Authentication authentication) throws MyaccessDeniedException {

        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {

            UserDetails userDetails = (UserDetails) principal;
            //得到当前的账号
            String username = userDetails.getUsername();

            //获取权限列表
            Collection<? extends GrantedAuthority> authority= userDetails.getAuthorities();

            List<Permission> listUrls = userService.getListPerByUsername(username);


            AntPathMatcher antPathMatcher = new AntPathMatcher();
            //当前访问路径
            String requestUri = request.getRequestURI();
            //提交类型
            String urlMethod = request.getMethod();
            //判断当前路径中是否在资源鉴权中
            boolean rs = listUrls.stream().anyMatch(item->{
                //判断URL是否匹配
                boolean hashAntPath = antPathMatcher.match(item.getPerApiUrl(),requestUri);

                //判断请求方式是否和数据库中匹配（数据库存储：GET,POST,PUT,DELETE）
                String dbMethod = item.getPerMethod();

                //处理null，万一数据库存值
                dbMethod = (dbMethod == null )? "": dbMethod;
                int hasMethod   = dbMethod.indexOf(urlMethod);

                System.out.println("hashAntPath = " + hashAntPath);
                System.out.println("hasMethod = " + hasMethod);
                System.out.println("hashAntPath && hasMethod = " + (hashAntPath && hasMethod !=-1));
                //两者都成立，返回真，否则返回假
                return hashAntPath && (hasMethod !=-1);
            });
            //返回
            if (rs) {
                return rs;
            }else {
                throw  new MyaccessDeniedException("您没有访问该API的权限！");
            }

        }else{
            throw  new MyaccessDeniedException("不是UserDetails类型！");
        }
    }
}

























