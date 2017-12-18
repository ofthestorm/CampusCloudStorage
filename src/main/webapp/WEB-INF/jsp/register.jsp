<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册页面</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div>
            ${msg}
        </div>
        <form role="form" method="post" action="/account/register">
            <label>用户ID
                <input type="text" class="form-control" id="u_id" name="uId" placeholder="请输入用户ID" value="${user.uId}">
            </label>
            <label>用户名
                <input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" value="${user.name}">
            </label>
            <label>用户密码
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入用户密码" value="${user.password}">
            </label>
            <label>账号类型
                <select class="form-control" name="type">
                    <option value="PRIVATE">私人账号</option>
                    <option value="PUBLIC">公共账号</option>
                </select>
            </label>
            <button type="submit" class="btn btn-default">注册</button>
        </form>
    </div>

</body>

<%@include file="common/foot.jsp"%>

</html>
