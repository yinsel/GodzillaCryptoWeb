package cn.yinsel.GodzillaCrypto.controller;

import cn.yinsel.GodzillaCrypto.service.GodzillaCryptoService;
import cn.yinsel.GodzillaCrypto.service.impl.GodzillaCryptoServiceImpl;
import cn.yinsel.GodzillaCrypto.util.CryptoUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 哥斯拉流量加解密API
 */
@WebServlet("/api/decrypt.action")
public class GodzillaCryptoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("text");
        String key = req.getParameter("key");
        String pass = req.getParameter("pass");
        String md5 = DigestUtils.md5Hex(pass + DigestUtils.md5Hex(key).substring(0,16));
        if (text == null || key == null) {
            resp.getWriter().println("<script>alert('参数错误');window.location='/index.jsp'</script>");
            return;
        }
        text = URLDecoder.decode(text, "UTF-8");
        text = URLDecoder.decode(text, "UTF-8");
        text = text.replace(" ", "+");
        byte[] result = null;
        GodzillaCryptoService godzillaCryptoService = new GodzillaCryptoServiceImpl();
        String regex = String.format("^(%s)(.*)(%s)$", md5.substring(0,16).toUpperCase(),md5.substring(16).toUpperCase());
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            text = matcher.group(2);
        }
        result = godzillaCryptoService.decrypt(text,key);
        if (result == null) {
            resp.getWriter().println("<script/>alert('解密失败,数据格式错误!');window.location='/index.jsp'<script>");
            return;
        }
        if (CryptoUtil.isValidClassFile(result)) {
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + "data.class" + "\"");
            resp.getOutputStream().write(result);
            return;
        }
        resp.getWriter().println(new String(result));
    }
}
