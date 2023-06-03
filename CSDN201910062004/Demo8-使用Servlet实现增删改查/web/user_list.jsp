<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="js/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/bootstrap.min.js"></script>
    <title>显示所有用户</title>
</head>
<body>
<%
    List<User> userList = (ArrayList<User>) session.getAttribute("userList");
%>
<a class="btn btn-info" href="add_user.jsp">添加用户</a>
<table class="table table-hover table-bordered">
    <tr>
        <td>序号</td>
        <td>用户名</td>
        <td>密码</td>
        <td>操作</td>
    </tr>
    <%
        for (User user : userList) {
    %>
    <tr>
        <td>
            <%=user.getId()%>
        </td>
        <td>
            <%=user.getUsername()%>
        </td>
        <td>
            <%=user.getPassword()%>
        </td>
        <td>
            <a class="btn btn-info" href="userServlet?action=del&id=<%=user.getId()%>">删除</a>
            &nbsp;&nbsp;
            <a class="btn btn-danger" href="userServlet?action=edit&id=<%=user.getId()%>">编辑</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
