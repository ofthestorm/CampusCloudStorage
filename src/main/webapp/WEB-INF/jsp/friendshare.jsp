<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>好友分享文件</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
<div class="container">
    <div>${msg}</div>

    <table class="table">
        <caption>我分享的文件</caption>
        <thead>
        <tr>
            <th>文件ID</th>
            <th>文件名</th>
            <th>大小</th>
            <th>分享备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${mySharedList}">
            <tr>
                <td>${file.fileHeader.fId}</td>
                <td>${file.fileHeader.name}</td>
                <td>${file.fileHeader.size}</td>
                <td>${file.userFileShare.remark}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-default my_shared_file_delete_btn" f_id="${file.fileHeader.fId}">取消分享</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table">
        <caption>好友分享的文件</caption>
        <thead>
        <tr>
            <th>文件ID</th>
            <th>文件名</th>
            <th>大小</th>
            <th>分享备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${friendSharedList}">
            <tr>
                <td>${file.fileHeader.fId}</td>
                <td>${file.fileHeader.name}</td>
                <td>${file.fileHeader.size}</td>
                <td>${file.userFileShare.remark}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-default friend_shared_file_download_btn" f_id="${file.fileHeader.fId}">下载</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>

<%@include file="common/foot.jsp"%>

<script type="text/javascript">
    $(function () {
        $('.friend_shared_file_download_btn').click(function () {
            var f_id=$(this).attr('f_id');
            var action='/file/' + f_id + '/download' ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var f_id_input = $('<input type="text" name="fId" />');
            f_id_input.attr('value',f_id);

            form.append(f_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })

        $('.my_shared_file_delete_btn').click(function(){
            var f_id=$(this).attr('f_id');
            var action='/file/' + ${uId} + '/' + ${friendId} + '/' + f_id + '/deletefriendshare' ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var f_id_input = $('<input type="text" name="fId" />');
            f_id_input.attr('value',f_id);

            form.append(f_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })
    })

</script>

</html>