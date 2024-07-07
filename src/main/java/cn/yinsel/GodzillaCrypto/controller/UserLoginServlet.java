package cn.yinsel.GodzillaCrypto.controller;

import cn.yinsel.GodzillaCrypto.model.User;
import cn.yinsel.GodzillaCrypto.service.UserService;
import cn.yinsel.GodzillaCrypto.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户登录API
 */
@WebServlet("/api/login.action")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        if (username == null || password == null) {
            out.println("<script>alert('参数错误!');window.location='/login.jsp'</script>");
            return;
        }
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, DigestUtils.md5Hex(password));
        if (user == null) {
            out.println("<script>alert('用户名密码错误!');window.location='/login.jsp'</script>");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            cookie.setDomain("localhost");
            resp.addCookie(cookie);
            out.println("<script>alert('登录成功');window.location='/index.jsp'</script>");
        }
    }
}
