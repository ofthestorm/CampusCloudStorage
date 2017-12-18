<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
<div class="container">
    <div>${msg}</div>
    <form role="form" method="post" action="/account/login">
        <div class="form-group">
            <label>用户ID
                <input type="text" class="form-control" name="uId" placeholder="请输入用户ID">
            </label>
            <label>用户密码
                <input type="password" class="form-control" name="password" placeholder="请输入用户密码">
            </label>
        </div>
        <button type="submit" class="btn btn-default">登录</button>
    </form>

    <form role="form" method="get" action="/account/register">
        <button type="submit" class="btn btn-default">注册</button>
    </form>
</div>
</body>

<%@include file="common/foot.jsp"%>

</html>