package org.example.security.handler;

import com.alibaba.fastjson.JSON;

import org.example.common.Result;
import org.example.common.enums.impl.AuthenticationEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yuxuan
 * @Description 登录失败操作
 * @Date 2023-06-24
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        String str = JSON.toJSONString(Result.failed(AuthenticationEnum.Login_Failure));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
