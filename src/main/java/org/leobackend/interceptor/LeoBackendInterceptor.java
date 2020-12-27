package org.leobackend.interceptor;

import org.leobackend.entity.TokenEntity;
import org.leobackend.utils.TokenUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2020-12-25.
 * 连接器，用于验证接口token
 *
 * @author Leo
 */
public class LeoBackendInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取位于请求头的token
        try {
            String token = request.getHeader("authorization");
            TokenEntity tokenEntity = TokenUtils.verifyToken(token);
            if (tokenEntity != null)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getRequestURL().append("api/auth/error").toString());
        return true;
    }

}
