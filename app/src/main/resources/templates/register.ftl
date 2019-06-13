<!DOCTYPE HTML>
<html>
<head>
    <title>注册</title>
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
            $("#register").click(function () {
                // 判断教师编号、教师密码、教师姓名是否有为空
                if ($("#teacherNumber").val() == "" || $("#teacherPassword").val() == "" || $("#teacherName").val() == "") {
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
                    // 若教师姓名为空
                    if ($("#teacherName").val() == "") {
                        $("#teacherNameError").html("&nbsp&nbsp&nbsp&nbsp教 师 姓 名 不 能 为 空 !")
                    } else {
                        $("#teacherNameError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    return
                } else if ($("#teacherNumber").val().length > 20 || $("#teacherPassword").val().length > 20 || $("#teacherName").val().length > 10) {
                    // 若教师编号长度大于20
                    if ($("#teacherNumber").val().length > 20) {
                        $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp教 师 编 号 最 大 长 度 限 制 为 20 !")
                    } else {
                        $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    // 若教师密码长度大于20
                    if ($("#teacherPassword").val().length > 20) {
                        $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp教 师 密 码 最 大 长 度 限 制 为 20 !")
                    } else {
                        $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    // 若教师姓名长度大于10
                    if ($("#teacherName").val().length > 10) {
                        $("#teacherNameError").html("&nbsp&nbsp&nbsp&nbsp教 师 姓 名 最 大 长 度 限 制 为 10 !")
                    } else {
                        $("#teacherNameError").html("&nbsp&nbsp&nbsp&nbsp*")
                    }
                    return
                } else {
                    $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp*")
                    $("#teacherPasswordError").html("&nbsp&nbsp&nbsp&nbsp*")
                    $("#teacherNameError").html("&nbsp&nbsp&nbsp&nbsp*")
                    $.ajax({
                        url: "/teacher/register",
                        type: "post",
                        data: JSON.stringify({
                            "teacherNumber": $("#teacherNumber").val(),
                            "teacherPassword": $("#teacherPassword").val(),
                            "teacherName": $("#teacherName").val()
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("注册成功") != -1) {
                                    alert(data)
                                    window.location = "/"
                                } else if (data.indexOf("重复注册") != -1) {
                                    $("#teacherNumberError").html("&nbsp&nbsp&nbsp&nbsp此 教 师 编 号 已 被 注 册 !")
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
        <h1>注 册</h1>
        <form>
            <input type="text" id="teacherNumber" name="teacherNumber" value="" placeholder="教 师 编 号"/>
            <span id="teacherNumberError" style="color: red">&nbsp&nbsp&nbsp&nbsp*</span>
            <input type="password" id="teacherPassword" name="teacherPassword" value="" placeholder="密 码"/>
            <span id="teacherPasswordError" style="color: red">&nbsp&nbsp&nbsp&nbsp*</span>
            <input type="text" id="teacherName" name="teacherName" value="" placeholder="教 师 姓 名"/>
            <span id="teacherNameError" style="color: red">&nbsp&nbsp&nbsp&nbsp*</span>
        </form>
        <div class="forgot">
            <input type="button" id="register" value="注册">
        </div>
    </div>
    <div class="login-bottom">
        <h3>有账号？ &nbsp;<a href="/">登陆</a>&nbsp 这里</h3>
    </div>
</div>
</body>
</html>