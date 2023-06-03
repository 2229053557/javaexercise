<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>添加用户</title>
</head>
<body>
<div class="container-fluid">
    <div class="form-horizontal">
        <form action="userServlet?action=add" method="post">
            <div class="form-group">
                <label class="text-muted col-lg-1">用户名：</label>
               <div class="col-lg-4">
                   <input class="form-control" type="text" name="username">
               </div>
            </div>
            <div class="form-group">
                <label class="text-muted col-lg-1">密码：</label>
                <div class="col-lg-4">
                    <input class="form-control" type="text" name="password">
                </div>
            </div>
            <div class="form-group">
                <input class="btn btn-success" type="submit" value="添加">
                <input class="btn btn-warning" type="reset" value="重置">
            </div>
        </form>
    </div>
</div>
</body>
</html>
