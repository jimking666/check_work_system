<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>教师端</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <style>
        /*登录界面样式*/
        .form-container {
            width: 300px;
            margin: 0 auto;
        }

        .page-title {
            text-align: center;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $(".meun-item").click(function () {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
            })
            $("#xsxq").click(function () {
                $(".gl").attr("style", "display:none");
                $(".xueshengxiangqing").attr("style", "display:block");
            })
            // 回到班级管理页面
            $("#goBackClazzManagement").click(function () {
                window.location = "/clazzManagement"
            })
            // 点击退出登陆触发事件
            $("#logout").click(function () {
                $.ajax({
                    url: '/teacher/logout',
                    type: 'get',
                    statusCode: {
                        200: function (data) {
                            window.location = "/"
                        }
                    }
                })
            })
            // 搜索学生
            $("#searchStudent").click(function () {
                $.ajax({
                    url: "/student/getStudentBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchStudentName": $("#searchStudentName").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/clazzStudentDetail"
                            }
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<div id="wrap">
    <div class="leftMeun show" id="leftMeun">
        <div id="logoDiv">教师批改端</div>
        <div id="personInfor">
            <p>
                <#if teacherDto??>
                    ${teacherDto.teacherName} 老师，您好
                <#else>
                    <a class="btn btn-default" href="/">请您先去登陆!</a>
                </#if>
            </p>
            <p>
                <#if teacherDto??>
                    <button class="btn btn-danger" id="logout">退出登陆</button>
                </#if>
            </p>
        </div>
        <div class="meun-title">教师操作</div>
        <div class="meun-item meun-item-active" id="xsxq">学生详情</div>
    </div>
    <div id="rightContent">
        <!--学生详情-->
        <div class="gl xueshengxiangqing">
            <div class="cx">
                <div class="input-group">
                    <input placeholder="输入学生名查询" class="form-control right-ss" id="searchStudentName"/>
                    <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchStudent">查询</button></span>
                </div>
            </div>
            <div class="biao">
                <table class="table table-striped">
                    <colgroup>
                        <col style="width: 23%">
                        <col style="width: 23%">
                        <col style="width: 23%">
                        <col style="width: 23%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>学生id</th>
                        <th>学号</th>
                        <th>学生姓名</th>
                        <th>所属班级</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if studentDtos??>
                            <#list studentDtos as studentDto>
                                <tr>
                                    <td>${studentDto.studentId}</td>
                                    <td>${studentDto.studentNumber}</td>
                                    <td>${studentDto.studentName}</td>
                                    <td>${studentDto.clazzName}</td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
            <div class="dao">
                <nav aria-label="Page navigation">
                    <button class="btn btn-primary" id="goBackClazzManagement">
                        主页面
                    </button>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>