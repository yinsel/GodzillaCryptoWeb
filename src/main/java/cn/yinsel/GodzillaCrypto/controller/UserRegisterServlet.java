package cn.yinsel.GodzillaCrypto.controller;

import cn.yinsel.GodzillaCrypto.model.User;
import cn.yinsel.GodzillaCrypto.service.UserService;
import cn.yinsel.GodzillaCrypto.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户注册API
 */
@WebServlet("/api/register.action")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (username == null || password == null) {
            out.println("<script>alert('参数错误!');window.location='/register.jsp'</script>");
            return;
        }
        UserService userService = new UserServiceImpl();
        User user = new User(username, DigestUtils.md5Hex(password));
        if (userService.register(user)) {
            out.println("<script>alert('注册成功!');window.location='/login.jsp'</script>");
        } else {
            out.println("<script>alert('注册失败!');window.location='/register.jsp'</script>");
        }
    }
}
