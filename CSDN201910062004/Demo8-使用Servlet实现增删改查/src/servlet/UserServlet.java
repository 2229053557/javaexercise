package servlet;

import bean.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException {
        // 设置请求的编码
        request.setCharacterEncoding("UTF-8");
        // 设置响应的编码
        response.setContentType("text/html;charset=UTF-8");
        // 获取action
        String action = request.getParameter("action");
        // 对action进行判断
        if (action.equals("userList")) {// 如果参数是userList则显示所有用户列表
            // 获取用户列表集合
            List<User> userList = userDao.selectAll();
            // 获取请求的session
            HttpSession session = request.getSession();
            // 设置session
            session.setAttribute("userList", userList);
            // 跳转user_list.jsp页面
            request.getRequestDispatcher("user_list.jsp").forward(request, response);
        } else if (action.equals("add")) {// 如果参数是add则是添加用户
            // 获取用户名
            String username = request.getParameter("username");
            // 获取密码
            String password = request.getParameter("password");
            // 添加到数据库
            int i = userDao.addUser(new User(username, password));
            // 对添加结果进行判断
            if (i > 0) {
                response.getWriter().println("<script>alert('添加用户成功！');window.location.href='userServlet?action=userList';</script>");
            } else {
                response.getWriter().println("<script>alert('添加用户失败！');window.location.href='add_user.jsp';</script>");
            }
        } else if (action.equals("del")) {// 如果参数是del则是删除用户
            // 获取要删除的id
            String id = request.getParameter("id");
            // 调用数据库方法进行删除操作
            int i = userDao.deleteById(Integer.parseInt(id));
            // 对删除结果进度判断
            if (i > 0) {
                response.getWriter().println("<script>alert('删除用户成功！');window.location.href='userServlet?action=userList';</script>");
            } else {
                response.getWriter().println("<script>alert('删除用户失败！');window.location.href='userServlet?action=userList';</script>");
            }
        } else if (action.equals("edit")) {// 如果参数是edit则是将要修改的参数保存到session中传输到edit_user.jsp页面进行显示
            // 获取要修改的id
            String id = request.getParameter("id");
            // 通过id查询用户
            User user = userDao.selectById(Integer.parseInt(id));
            // 保存到session中，然后在edit_user.jsp中使用
            request.getSession().setAttribute("user", user);
            // 跳转到edit_user.jsp页面
            request.getRequestDispatcher("edit_user.jsp").forward(request, response);
        } else if (action.equals("update")) {// 如果参数是update则是将修改后的数据保存到数据库中
            // 获取id
            String id = request.getParameter("id");
            // 获取修改后的用户名
            String username = request.getParameter("username");
            // 获取修改后的密码
            String password = request.getParameter("password");
            // 将修改更新到数据库中
            int i = userDao.updateUser(new User(Integer.parseInt(id), username, password));
            // 对更新结果进行判断
            if (i > 0) {// 如果更新成功
                response.getWriter().println("<script>alert('编辑用户成功！');window.location.href='userServlet?action=userList';</script>");
            } else {
                response.getWriter().println("<script>alert('编辑用户失败！');window.location.href='userServlet?action=userList';</script>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
