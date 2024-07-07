package cn.yinsel.GodzillaCrypto.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求记录监听器
 */
@WebListener
public class LogListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest req = (HttpServletRequest) servletRequestEvent.getServletRequest();
        System.out.println(req.getMethod() + " " + req.getRequestURL());
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
