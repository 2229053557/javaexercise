package dao;

import bean.User;
import utils.DBUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Serializable {
    /**
     * 查找数据库中的所有记录
     *
     * @return 返回查询结果集合
     */
    public List<User> selectAll() {
        // 存储查询结果集的集合
         List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps=null ;
        ResultSet rs = null;
            try {
            // 获取数据库连接对象
            conn = DBUtils.getConnection();
            // SQL语句
            String sql = "select * from users";
            // 创建数据库执行对象
            ps = conn.prepareStatement(sql);
            // 获取结果集对象
            rs = ps.executeQuery();
            // 对结果集进行遍历
            while (rs.next()) {
                // 实例化User对象
                User user = new User();
                // 进行赋值
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                // 将User对象添加到集合中
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, ps, rs);
        }
        return userList;
    }

    /**
     * 查询用户通过用户id
     *
     * @param id 用户id
     * @return 返回查询用户
     */
    public User selectById(int id) {
        // 定义一个User对象
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接对象
            conn = DBUtils.getConnection();
            // SQL语句
            String sql = "select id, username, password from users where id=?";
            // 创建数据库执行对象
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setInt(1, id);
            // 获取结果集对象
            rs = ps.executeQuery();
            // 对结果集进行遍历
            while (rs.next()) {
                // 实例化User对象
                user=new User();
                // 对User进行赋值
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, ps, rs);
        }
        return user;
    }

    /**
     * 通过id删除记录
     *
     * @param id 用户id
     * @return 返回受影响行数
     */
    public int deleteById(int id) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取数据库连接对象
            conn = DBUtils.getConnection();
            // SQL语句
            String sql = "delete from users where id=?";
            // 创建数据库执行对象
            ps = conn.prepareStatement(sql);
            // 对参数进行赋值
            ps.setInt(1, id);
            // 执行删除
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, ps, null);
        }
        return count;
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 返回受影响行数
     */
    public int addUser(User user) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取数据库连接对象
            conn = DBUtils.getConnection();
            // SQL语句
            String sql = "insert into users(username, password) values (?,?);";
            // 创建数据库执行对象
            ps = conn.prepareStatement(sql);
            // 对参数进行赋值
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            // 执行增加
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, ps, null);
        }
        return count;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 返回受影响行数
     */
    public int updateUser(User user) {
        // 受影响行数
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取数据库连接对象
            conn = DBUtils.getConnection();
            // SQL语句
            String sql = "update users set username=?,password=? where id=?";
            // 创建数据库执行对象
            ps = conn.prepareStatement(sql);
            // 对参数进行赋值
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            // 执行更新
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, ps, null);
        }
        return count;
    }
}
