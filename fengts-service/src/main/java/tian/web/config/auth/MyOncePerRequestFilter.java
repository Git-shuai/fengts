package tian.web.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tian.web.Constant;
import tian.web.components.JwtTokenUtil;
import tian.web.service.user.auth.AuthUserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义拦截器 ，拦截token
 *
 * @author tian
 * @date 2020/12/5
 */
@Component
public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    AuthUserDetailsServiceImpl authUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerToken = request.getHeader(Constant.HEADER_TOKEN);
        logger.info("headerToken = " + headerToken);
        logger.info("request getMethod = " + request.getMethod());

        if (!StringUtils.isEmpty(headerToken)) {
            String token = headerToken.replace("Bearer", "").trim();
            System.out.println("token = " + token);

            boolean check = false;
            try {
                check = jwtTokenUtil.isTokenExpired(token);
            } catch (Exception e) {
                new Throwable("令牌已过期，请重新登录。" + e.getMessage());
            }

            if (!check) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);
                    boolean validate = false;
                    try {
                        validate = jwtTokenUtil.validateToken(token, userDetails);
                    } catch (Exception e) {
                        new Throwable("验证token无效:" + e.getMessage());
                    }
                    if (validate) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
