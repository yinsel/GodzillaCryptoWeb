package cn.yinsel.GodzillaCrypto.dao.impl;

import cn.yinsel.GodzillaCrypto.dao.UserDao;
import cn.yinsel.GodzillaCrypto.model.User;
import cn.yinsel.GodzillaCrypto.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 用户Dao层实现类
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean register(User user) {
        Connection conn = JdbcUtil.getConnection();
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            int rows = runner.update(conn, sql, user.getUsername(), user.getPassword());
            if (rows == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入用户失败！");
        return false;
    }

    @Override
    public User login(String username, String password) {
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from users where username = ? and password = ?";
        QueryRunner queryRunner = new QueryRunner();
        User user = null;
        try {
            user = queryRunner.query(conn, sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("用户查询失败!");
        }
        return user;
    }
}
