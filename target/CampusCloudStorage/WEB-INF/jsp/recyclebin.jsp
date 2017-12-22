<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

<div class="container">

    <div>${msg}</div>
    <form role="form" method="post" action="/home/${rootDir}">
        <button type="submit" class="btn btn-default">首页</button>
    </form>
    <form role="form" method="post" action="/friend/${uId}">
        <button type="submit" class="btn btn-default">好友</button>
    </form>
    <form role="form" method="post" action="/group/${uId}">
        <button type="submit" class="btn btn-default">群组</button>
    </form>
    <form role="form" method="post" action="/recyclebin/${recyclebin}">
        <button type="submit" class="btn btn-default">回收站</button>
    </form>

    <form id="dir_recover_form" role="form" method="post">
        <h3>选择移入文件夹</h3>
        <ul class="list-group" id="dir_move_list">
        </ul>
    </form>

    <form id="file_recover_form" role="form" method="post">
        <h3>选择移入文件夹</h3>
        <ul class="list-group" id="file_move_list">
        </ul>
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
                        <button class="btn btn-default dir_recover_form_open_btn" d_id="${dir.dId}">恢复至</button>
                        <button class="btn btn-default dir_delete_btn" d_id="${dir.dId}">彻底删除</button>
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
                        <button class="btn btn-default file_delete_btn" f_id="${file.fId}">彻底删除</button>
                        <button class="btn btn-default file_recover_form_open_btn" f_id="${file.fId}">移动至</button>
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
        var src_f_id;

        $('#dir_recover_form').hide();
        $('#file_recover_form').hide();


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


        //删除文件夹
        $('.dir_delete_btn').click(function () {
            var d_id=$(this).attr('d_id');
            var action='/recyclebin/' + d_id + '/dir/delete' ;
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
            var action='/recyclebin/' + d_id ;
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
            var action='/recyclebin/' + d_id ;
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


        //恢复文件夹 ajax
        $('.dir_recover_form_open_btn').click(function () {
            src_d_id = $(this).attr('d_id');
            var action="/recyclebin/"+src_d_id+"/dir/";
            $('#dir_recover_form').attr("action",action);
            $('#dir_recover_form').show();

            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/${rootDir}/enter",
                data:{
                    srcId:src_d_id,
                    dId:${rootDir},
                    moveType:"dir"
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
                    dId:$(this).attr("d_id"),
                    moveType:"dir"
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#dir_move_list").html(htmlobj.responseText);
        })

        $(document).delegate('#dir_recover_form_close_btn','click',function () {
            $('#dir_recover_form').hide();
            $('.dir_recover_form_open_btn').show();
        })

        $(document).delegate('#dir_move_back_btn','click',function () {
            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/"+$(this).attr("d_id")+"/enter",
                data:{
                    srcDId:src_d_id,
                    dId:$(this).attr("d_id"),
                    moveType:"dir"
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
            var action = $("#dir_recover_form").attr("action")+parentId+"/recover";
            $("#dir_recover_form").attr("action",action);
            $("#dir_recover_form").submit();
        })

        //恢复文件 ajax
        $('.file_recover_form_open_btn').click(function () {
            src_f_id = $(this).attr('f_id');
            var action="/recyclebin/"+src_f_id+"/file/";
            $('#file_recover_form').attr("action",action);
            $('#file_recover_form').show();

            var htmlobj=$.ajax({
                url:"/home/"+src_f_id+"/dir/${rootDir}/enter",
                data:{
                    srcId:src_f_id,
                    dId:${rootDir},
                    moveType:"file"
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#file_move_list").html(htmlobj.responseText);
            $(this).hide();
        })

        $(document).delegate('.file_move_enter_btn','click',function () {
            var htmlobj=$.ajax({
                url:"/home/"+src_f_id+"/dir/"+$(this).attr("d_id")+"/enter",
                data:{
                    srcId:src_f_id,
                    dId:$(this).attr("d_id"),
                    moveType:"file"
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#file_move_list").html(htmlobj.responseText);
        })

        $(document).delegate('#file_move_form_close_btn','click',function () {
            $('#file_move_form').hide();
            $('.file_recover_form_open_btn').show();
        })

        $(document).delegate('#file_move_btn','click',function () {
            var parentId = $(this).attr("d_id");
            var action = $("#file_recover_form").attr("action")+parentId+"/recover";
            $("#file_recover_form").attr("action",action);
            $("#file_recover_form").submit();
        })

        $(document).delegate('#file_move_back_btn','click',function () {
            var htmlobj=$.ajax({
                url:"/home/"+src_d_id+"/dir/"+$(this).attr("d_id")+"/enter",
                data:{
                    srcDId:src_d_id,
                    dId:$(this).attr("d_id"),
                    moveType:"file"
                },
                async:false,
                dataType: "text",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"POST"
            });
            $("#dir_move_list").html(htmlobj.responseText);
        })

    })

</script>

</html>
