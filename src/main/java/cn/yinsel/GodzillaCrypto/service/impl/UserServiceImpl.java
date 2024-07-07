package cn.yinsel.GodzillaCrypto.service.impl;

import cn.yinsel.GodzillaCrypto.dao.UserDao;
import cn.yinsel.GodzillaCrypto.dao.impl.UserDaoImpl;
import cn.yinsel.GodzillaCrypto.model.User;
import cn.yinsel.GodzillaCrypto.service.UserService;

/**
 * 用户Service实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public boolean register(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.register(user);
    }

    @Override
    public User login(String username, String password) {
        UserDao userDao = new UserDaoImpl();
        return userDao.login(username,password);
    }
}
