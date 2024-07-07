package cn.yinsel.GodzillaCrypto.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtil {
    private static final String jdbcUrl;
    private static final String jdbcUsername;
    private static final String jdbcPassword;

    static {
        Properties props = new Properties();
        try (FileInputStream dbConf = new FileInputStream(Objects.requireNonNull(JdbcUtil.class.getClassLoader().getResource("db.properties")).getFile())) {
            props.load(dbConf);
            jdbcUrl = props.getProperty("jdbc.url");
            jdbcUsername = props.getProperty("jdbc.username");
            jdbcPassword = props.getProperty("jdbc.password");
        } catch (IOException e) {
            throw new RuntimeException("配置文件不存在!", e);
        }
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驱动加载失败!",e);
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败!",e);
        }
        return conn;
    }
}
