package utils;

import java.sql.*;

public class DBUtils {
    /**
     * 获取数据库连接对象
     * @return 返回数据库连接对象
     */
    public static Connection getConnection(){
        Connection connection=null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdemo","root","123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放数据库连接资源
     * @param connection 数据库连接对象
     * @param statement 数据库执行对象
     * @param resultSet 数据库结果集对象
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        // 关闭resultSet
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭statement
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭connection
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
