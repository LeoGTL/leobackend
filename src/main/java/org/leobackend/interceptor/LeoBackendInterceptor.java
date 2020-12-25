package org.leobackend.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created on 2020-12-25.
 * 连接器，用于验证接口token
 *
 * @author Leo
 */
public class LeoBackendInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre...................");

        // 获取位于请求头的token
        String token = request.getParameter("token");
        String secret = "12345654321";

        if (secret.equals(token)) {
            System.out.println("验签成功");
            return true;
        }
        System.out.println("验签失败");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        System.out.println("post.............................");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after..............................");
    }
}
