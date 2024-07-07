package cn.yinsel.GodzillaCrypto.service;

import cn.yinsel.GodzillaCrypto.model.User;

public interface UserService {
    boolean register(User user);
    User login(String username, String password);
}
