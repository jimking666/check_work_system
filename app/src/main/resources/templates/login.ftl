<!DOCTYPE HTML>
<html>
<head>
    <title>登陆</title>
    <!-- Custom Theme files -->
    <link href="css/login.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script>
        $(function () {
            $("#login").click(function () {
                // 若教师编号为空或教师密码为空
                if ($("#teacherNumber").val() == "" || $("#teacherPassword").val() == "") {
                    // 若教师编号为空
                    if ($("#teacherNumber").val() == "") {
                        $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp教 师 编 号 不 能 为 空 !")
                    } else {
                        $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    // 若教师密码为空
                    if ($("#teacherPassword").val() == "") {
                        $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp密 码 不 能 为 空 !")
                    } else {
                        $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    return
                } else {
                    // 若都不为空则提交表单
                    $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp*")
                    $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp*")
                    $.ajax({
                        url: "/teacher/login",
                        type: "post",
                        data: JSON.stringify({
                            "teacherNumber": $("#teacherNumber").val(),
                            "teacherPassword": $("#teacherPassword").val()
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("登陆成功") != -1) {
                                    window.location = "/workManagement"
                                } else if (data.indexOf("账号或密码错误") != -1) {
                                    $("#teacherNumber").val("")
                                    $("#teacherPassword").val("")
                                    $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp教 师 编 号 或 密 码 错 误 !")
                                }
                            }
                        }
                    })
                }
            })
        })
    </script>
</head>
<body>
<div class="login">
    <h2>欢迎进入教师端</h2>
    <div class="login-top">
        <h1>登 陆</h1>
        <form>
            <input type="text" id="teacherNumber" name="teacherNumber" value="" placeholder="教 师 编 号">
            <span id="teacherNumberError" style="color: red">&nbsp&nbsp&nbsp&nbsp*</span>
            <input type="password" id="teacherPassword" name="teacherPassword" value="" placeholder="密 码">
            <span id="teacherPasswordError" style="color: red">&nbsp&nbsp&nbsp&nbsp*</span>
            <div class="forgot">
                <input type="button" id="login" value="登陆">
            </div>
        </form>
    </div>
    <div class="login-bottom">
        <h3>新用户？ &nbsp;<a href="/register">注册</a>&nbsp 这里</h3>
    </div>
</div>
</body>
</html>