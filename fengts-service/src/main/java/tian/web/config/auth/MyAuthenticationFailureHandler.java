package tian.web.config.auth;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import tian.web.Result;
import tian.web.enums.ResCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败操作
 */
@Component
public class MyAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        //装入token
        Result<Object> data = new Result<>();
        data.setCode(ResCode.ERROR_CODE);
        data.setMessage("登录失败,"+e.getMessage());
        //输出
        this.WriteJSON(request, response, data);
    }
}