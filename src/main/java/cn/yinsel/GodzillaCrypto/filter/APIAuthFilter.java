package cn.yinsel.GodzillaCrypto.filter;

import cn.yinsel.GodzillaCrypto.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * API鉴权过滤器
 */
@WebFilter(urlPatterns = {"/api/decrypt.action","/api/logout.action"},filterName = "APIAuthFilter02")
public class APIAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{\"error\": \"401 Unauthorized\"}");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
