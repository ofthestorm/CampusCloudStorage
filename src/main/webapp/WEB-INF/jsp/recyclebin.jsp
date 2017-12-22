<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

<div class="container">

    <div>${msg}</div>
    <div class="btn-group">
        <button class="btn btn-default" id="delete_all_btn">清空回收站</button>
    </div>

    <form id="dir_move_form" role="form" method="post">
        <h3>选择移入文件夹</h3>
        <ul class="list-group" id="dir_move_list">
        </ul>
    </form>

    <form id="friend_share_form" role="form" method="post">
        <h3>选择好友</h3>
        <select class="form-control" id="friend_id_select" name="friendId">
            <c:forEach var="friend" items="${friendList}">
                <option value="${friend.uId}">(${friend.uId}) ${friend.name}</option>
            </c:forEach>
        </select>
        <input class="form-control" name="remark" placeholder="请输入备注">
        <button type="button" id="friend_share_btn" class="btn btn-default">分享</button>
        <button type="button" id="friend_share_form_close_btn" class="btn btn-default">取消</button>
    </form>

    <form id="group_share_form" role="form" method="post">
        <h3>选择群组</h3>
        <select class="form-control" id="group_id_select" name="gId">
            <c:forEach var="group" items="${groupList}">
                <option value="${group.gId}">(${group.gId}) ${group.name}</option>
            </c:forEach>
        </select>
        <input class="form-control" name="remark" placeholder="请输入备注">
        <button type="button" id="group_share_btn" class="btn btn-default">分享</button>
        <button type="button" id="group_share_form_close_btn" class="btn btn-default">取消</button>
    </form>


    <div>
        当前路径:
        <c:forEach var="dir" items="${pathList}">
            <span class="path_span" d_id="${dir.dId}"> ${dir.name}> </span>
        </c:forEach>
    </div>

    <table class="table">
        <caption>文件夹</caption>
        <thead>
        <tr>
            <th>文件夹名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dir" items="${dirList}">
            <tr>
                <td>${dir.name}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-default dir_open_btn" d_id="${dir.dId}">打开</button>
                        <button class="btn btn-default dir_rename_form_open_btn" d_id="${dir.dId}" name="${dir.name}">重命名</button>
                        <button class="btn btn-default dir_move_form_open_btn" d_id="${dir.dId}">移动至</button>
                        <button class="btn btn-default dir_delete_btn" d_id="${dir.dId}">删除</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table">
        <caption>文件</caption>
        <thead>
        <tr>
            <th>文件ID</th>
            <th>文件名</th>
            <th>大小</th>
            <th>上传日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${fileHeaderList}">
            <tr>
                <td>${file.fId}</td>
                <td>${file.name}</td>
                <td>${file.size}</td>
                <td>${file.submitTime}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-default file_delete_btn" f_id="${file.fId}">删除</button>
                        <button class="btn btn-default file_download_btn" f_id="${file.fId}">下载</button>
                        <button class="btn btn-default friend_share_form_open_btn" f_id="${file.fId}">分享给好友</button>
                        <button class="btn btn-default group_share_form_open_btn" f_id="${file.fId}">分享到群组</button>
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
        var src_d_id;

        $('#dir_create_form').hide();
        $('#dir_rename_form').hide();
        $('#dir_move_form').hide();
        $('#file_upload_form').hide();
        $('#friend_share_form').hide();
        $('#group_share_form').hide();

        //上传文件
        $('#file_upload_form_open_btn').click(function () {
            $('#file_upload_form').show();
            $(this).hide();
        })

        $('#form_upload_form_close_btn').click(function () {
            $('#file_upload_form').hide();
            $('#file_upload_form_open_btn').show();
        })

        //下载文件
        $('.file_download_btn').click(function () {
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

        //删除文件
        $('.file_delete_btn').click(function () {
            var f_id=$(this).attr('f_id');
            var action='/file/' + f_id + '/delete' ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var f_id_input = $('<input type="text" name="fId" />');

            form.append(f_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })

        //新建文件夹
        $('#dir_create_form_open_btn').click(function () {
            $('#dir_create_form').show();
            $(this).hide();
        })

        $('#dir_create_form_close_btn').click(function () {
            $('#dir_create_form').hide();
            $('#dir_create_form_open_btn').show();
        })

        //删除文件夹
        $('.dir_delete_btn').click(function () {
            var d_id=$(this).attr('d_id');
            var action='/home/' + d_id + '/dir/delete' ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var d_id_input = $('<input type="text" name="dId" />');
            d_id_input.attr('value',d_id);

            form.append(d_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })

        //路径显示
        $('.path_span').click(function () {
            var d_id=$(this).attr('d_id');
            var action='/home/' + d_id ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var d_id_input = $('<input type="text" name="dId" />');
            d_id_input.attr('value',d_id);

            form.append(d_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })

        $('.dir_open_btn').click(function () {
            var d_id=$(this).attr('d_id');
            var action='/home/' + d_id ;
            var form = $('<form></form>');

            form.attr('action', action);
            form.attr('method', 'post');
            form.attr('target', '_self');

            var d_id_input = $('<input type="text" name="dId" />');
            d_id_input.attr('value',d_id);

            form.append(d_id_input);

            form.appendTo("body").submit();
            form.remove();
            return false;
        })

        //重命名
        $('.dir_rename_form_open_btn').click(function () {
            $('#rename_input').val($(this).attr('name'));
            var action="/home/"+$(this).attr('d_id')+"/dir/rename";
            $('#dir_rename_form').attr("action",action);
            $('#dir_rename_form').show();
            $(this).hide();
        })

        $('#dir_rename_form_close_btn').click(function () {
            $('#dir_rename_form').hide();
            $('#dir_rename_form_open_btn').show();
        })

        //移动文件夹 ajax
        $('.dir_move_form_open_btn').click(function () {
            src_d_id = $(this).attr('d_id');
            var action="/home/"+src_d_id+"/dir/";
            $('#dir_move_form').attr("action",action);
            $('#dir_move_form').show();

            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/${rootDir}/enter",
                data:{
                    srcDId:src_d_id,
                    dId:${rootDir}
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#dir_move_list").html(htmlobj.responseText);
            $(this).hide();
        })

        $(document).delegate('.dir_move_enter_btn','click',function () {
            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/"+$(this).attr("d_id")+"/enter",
                data:{
                    srcDId:src_d_id,
                    dId:$(this).attr("d_id")
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#dir_move_list").html(htmlobj.responseText);
        })

        $(document).delegate('#dir_move_form_close_btn','click',function () {
            $('#dir_move_form').hide();
            $('.dir_move_form_open_btn').show();
        })

        $(document).delegate('#dir_move_back_btn','click',function () {
            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/"+$(this).attr("d_id")+"/enter",
                data:{
                    srcDId:src_d_id,
                    dId:$(this).attr("d_id")
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#dir_move_list").html(htmlobj.responseText);
        })

        $(document).delegate('#dir_move_btn','click',function () {
            var parentId = $(this).attr("d_id");
            var action = $("#dir_move_form").attr("action")+parentId+"/move";
            $("#dir_move_form").attr("action",action);
            $("#dir_move_form").submit();
        })

        //分享朋友
        $('.friend_share_form_open_btn').click(function () {
            $('#friend_share_form').show();
            $('#friend_share_form').attr('f_id',$(this).attr('f_id'));
            $(this).hide();
        })

        $('#friend_share_form_close_btn').click(function () {
            $('#friend_share_form').hide();
            $('.friend_share_form_open_btn').show();
        })

        $('#friend_share_btn').click(function () {
            var friend_id=$('#friend_id_select').val();
            if(isNaN(friend_id)){
                alert("请选择一个好友！");
                return false;
            }
            var f_id = $('#friend_share_form').attr('f_id');
            var action = '/file/${uId}/' + friend_id + '/' + f_id + '/friendshare';
            $('#friend_share_form').attr('action',action);

            $('#friend_share_form').submit();
            return false;
        })

        //分享至群组
        $('.group_share_form_open_btn').click(function () {
            $('#group_share_form').show();
            $('#group_share_form').attr('f_id',$(this).attr('f_id'));
            $(this).hide();
        })

        $('#group_share_form_close_btn').click(function () {
            $('#group_share_form').hide();
            $('.group_share_form_open_btn').show();
        })

        $('#group_share_btn').click(function () {
            var g_id=$('#group_id_select').val();
            if(isNaN(g_id)){
                alert("请选择一个群组！");
                return false;
            }
            var f_id = $('#group_share_form').attr('f_id');
            var action = '/group/${uId}/' + g_id + '/' + f_id + '/groupshare';
            $('#friend_share_form').attr('action',action);

            $('#friend_share_form').submit();
            return false;
        })
    })

</script>

</html>
