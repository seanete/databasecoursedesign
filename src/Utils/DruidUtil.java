package Utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class DruidUtil {

    //定义成员变量DataSource
    public static DataSource ds;
    static{
        try {
            //加载配置文件
            InputStream in = new BufferedInputStream(new FileInputStream("src/library/druid.properties"));
            Properties prop = new Properties();
            prop.load(in); //加载配置文件
            ds = DruidDataSourceFactory.createDataSource(prop); //获取连接池对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //释放数据库资源
    public static void close(Statement stmt, Connection conn) throws SQLException {
        close(null,stmt,conn);
    }

    //关闭连接
    public static void close(ResultSet rs , Statement stmt, Connection conn) throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(stmt != null){
            stmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }
}

