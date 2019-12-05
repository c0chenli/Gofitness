package com.flagcamp.gofitness.configuration;

import com.flagcamp.gofitness.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class JWTFilter extends GenericFilterBean {

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }


        if (allowRequestWithoutToken(request)) {
//            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (token == null || !tokenService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                Map<String, String> userInfo = tokenService.getUserInfoFromToken(token);
                request.setAttribute("userEmail", userInfo.get("userEmail"));
                request.setAttribute("role", userInfo.get("role"));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().contains("/getRoomId") || request.getRequestURI().contains("/signup") || request.getRequestURI().contains("/signin") || request.getRequestURI().contains("/getAllTrainerDemo")) {
            return true;
        }
        return false;
    }
}
