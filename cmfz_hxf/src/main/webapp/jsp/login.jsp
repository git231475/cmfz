<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>持明法洲后台登录页面</title>
    <script >
        function formsub() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/login",
                type:"post",
                dataType:"json",
                data:$("#loginForm").serialize(),
                success:function(data){
                    /*
                    alert(data.code)
                    */
                    if(data.code==200){
                        location.href="${pageContext.request.contextPath}/jsp/main.jsp"
                    }else{
                        $("#message").html(data.message)
                    }
                }
            })
        }

    </script>
</head>
<body>
<%--action="javascript:void(0)执行JS函数，，不让跳转--%>
<form id="loginForm" action="javascript:void(0)">
    <div class="form-group">
        <label for="username">账号 </label>
        <input type="tsxt" name="username" class="form-control" id="username">
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>
   <button class="btn btn-default" onclick="formsub()">提交</button>
    <span id="message" style="color:red"></span>
</form>
</body>
</html>