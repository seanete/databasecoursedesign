package DAO.Implement;

import DAO.Interface.DAO;
import Utils.DruidUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DAObase implements DAO{
  /*  public Connection getConnection(){
        Connection conn1=null;
        try {
            //1. 创建Properties对象
            Properties properties = new Properties();
            //2. 将配置文件转换成字节输入流
            InputStream is = DAObase.class.getClassLoader().getResourceAsStream("src/Library/druid.properties");
            //3. 使用properties对象加载is
            properties.load(is);
            //druid底层是使用的工厂设计模式，去加载配置文件，创建DruidDataSource对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn1 = dataSource.getConnection();
            is.close();//关流
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn1;
    }*/
  public Connection getConnection() {
      try {
          return DruidUtil.ds.getConnection();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }
}
