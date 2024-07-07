package cn.yinsel.GodzillaCrypto.dao;

import cn.yinsel.GodzillaCrypto.model.User;

public interface UserDao {
    boolean register(User user);

    User login(String username, String password);
}
