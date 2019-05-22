<!doctype html>
<html>
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
            $("#zygl").click(function () {
                $(".gl").attr("style", "display:none");
                $(".zuoyeguanli").attr("style", "display:block");
            })
            $("#kcgl").click(function () {
                $(".gl").attr("style", "display:none");
                $(".kechengguanli").attr("style", "display:block");
            })
            $("#bjgl").click(function () {
                $(".gl").attr("style", "display:none");
                $(".banjiguanli").attr("style", "display:block");
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

            // 搜索课程教师班级
            $("#searchCourseTeacherClazz").click(function () {
                $.ajax({
                    url: "/courseTeacherClazz/getCourseTeacherClazzBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchCourseName": $("#searchCourseName").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/workManagement"
                            }
                        }
                    }
                })
            })

            // 创建课程教师班级事件
            $("#createCourseTeacherClazz").click(function () {
                if ($("#courseId2").val() == "" || $("#clazzId2").val() == "") {
                    // 若课程名称为空
                    if ($("#courseId2").val() == "") {
                        $("#courseId2Error").html("&nbsp&nbsp课 程 名 称 不 能 为 空 !")
                    } else {
                        $("#courseId2Error").html("&nbsp&nbsp*")
                    }
                    // 若班级名称为空
                    if ($("#clazzId2").val() == "") {
                        $("#clazzId2Error").html("&nbsp&nbsp班 级 名 称 不 能 为 空 !")
                    } else {
                        $("#clazzId2Error").html("&nbsp&nbsp*")
                    }
                    return
                } else {
                    $.ajax({
                        url: "/courseTeacherClazz/create",
                        type: "post",
                        data: JSON.stringify({
                            "courseId": $("#courseId2").val(),
                            "teacherId": $("#teacherId").val(),
                            "clazzId": $("#clazzId2").val()
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("创建成功") != -1) {
                                    alert("创 建 成 功 !")
                                    window.location = "/workManagement"
                                } else if (data.indexOf("重复创建") != -1) {
                                    $("#courseId2Error").html("&nbsp&nbsp*")
                                    $("#clazzId2Error").html("&nbsp&nbsp已 存 在 此 课 程 教 师 班 级 关 联 !")
                                }
                            }
                        }
                    })
                }
            })

            // 关闭创建课程教师班级窗口触发事件
            $("#closeCreateCourseTeacherClazzWindow").click(function () {
                $("#courseId2Error").html("&nbsp&nbsp*")
                $("#courseId2").val("")
                $("#clazzId2Error").html("&nbsp&nbsp*")
                $("#clazzId2").val("")
            })

            // 点击课程教师班级确认删除触发事件
            $("#deleteCourseTeacherClazz").click(function () {
                $.ajax({
                    url: "/courseTeacherClazz/delete",
                    type: "post",
                    data: JSON.stringify({
                        "courseTeacherClazzId": $("#courseTeacherClazzId").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("删除成功") != -1) {
                                alert("删 除 成 功 !")
                                window.location = "/workManagement"
                            }
                        }
                    }
                })
            })

            // 搜索课程
            $("#searchCourse").click(function () {
                $.ajax({
                    url: "/course/getCourseBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchCourseName": $("#searchCourseName1").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/courseManagement"
                            }
                        }
                    }
                })
            })

            // 创建课程事件
            $("#createCourse").click(function () {
                if ($("#courseName").val() == "") {
                    $("#courseNameError").html("&nbsp&nbsp课 程 名 称 不 能 为 空 !")
                } else {
                    $.ajax({
                        url: "/course/create",
                        type: "post",
                        data: JSON.stringify({
                            "courseName": $("#courseName").val(),
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("创建成功") != -1) {
                                    alert("创 建 成 功 !")
                                    window.location = "/courseManagement"
                                } else if (data.indexOf("重复创建") != -1) {
                                    $("#courseName").html("")
                                    $("#courseNameError").html("&nbsp&nbsp已 存 在 此 课 程 !")
                                }
                            }
                        }
                    })
                }
            })

            // 关闭创建课程窗口触发事件
            $("#closeCreateCourseWindow").click(function () {
                $("#courseNameError").html("&nbsp&nbsp*")
                $("#courseName").val("")
            })

            // 点击课程确认删除触发事件
            $("#deleteCourse").click(function () {
                $.ajax({
                    url: "/course/delete",
                    type: "post",
                    data: JSON.stringify({
                        "courseId": $("#courseId").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("删除成功") != -1) {
                                alert("删 除 成 功 !")
                                window.location = "/courseManagement"
                            }
                        }
                    }
                })
            })

            // 搜索班级
            $("#searchClazz").click(function () {
                $.ajax({
                    url: "/clazz/getClazzBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchClazzName": $("#searchClazzName").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/clazzManagement"
                            }
                        }
                    }
                })
            })

            // 创建班级事件
            $("#createClazz").click(function () {
                if ($("#clazzName").val() == "") {
                    $("#clazzNameError").html("&nbsp&nbsp班 级 名 称 不 能 为 空 !")
                } else {
                    $.ajax({
                        url: "/clazz/create",
                        type: "post",
                        data: JSON.stringify({
                            "clazzName": $("#clazzName").val(),
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("创建成功") != -1) {
                                    alert("创 建 成 功 !")
                                    window.location = "/clazzManagement"
                                } else if (data.indexOf("重复创建") != -1) {
                                    $("#clazzName").html("")
                                    $("#clazzNameError").html("&nbsp&nbsp已 存 在 此 班 级 !")
                                }
                            }
                        }
                    })
                }
            })

            // 关闭创建课程窗口触发事件
            $("#closeCreateClazzWindow").click(function () {
                $("#clazzNameError").html("&nbsp&nbsp*")
                $("#clazzName").val("")
            })

            // 点击班级确认删除触发事件
            $("#deleteClazz").click(function () {
                $.ajax({
                    url: "/clazz/delete",
                    type: "post",
                    data: JSON.stringify({
                        "clazzId": $("#clazzId").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("删除成功") != -1) {
                                alert("删 除 成 功 !")
                                window.location = "/clazzManagement"
                            }
                        }
                    }
                })
            })
        })

        // 点击创建课程教师班级触发事件
        function addTeacherId(teacherId) {
            $("#teacherId").val(teacherId)
        }

        // 点击课程教师班级删除触发事件
        function addCourseTeacherClazzId(courseTeacherClazzId) {
            $("#courseTeacherClazzId").val(courseTeacherClazzId)
        }

        // 点击课程删除触发事件
        function addCourseId(courseId) {
            $("#courseId").val(courseId)
        }

        // 点击班级删除触发事件
        function addClazzId(clazzId) {
            $("#clazzId").val(clazzId)
        }

        // 点击学生详情触发事件
        function studentDetail(clazzId) {
            $.ajax({
                url: "/student/studentDetail",
                type: "post",
                data: JSON.stringify({
                    "clazzId": clazzId
                }),
                contentType: "application/json;charset=utf-8",
                statusCode: {
                    200: function (data) {
                        if (data.indexOf("学生详情查询成功") != -1) {
                            window.location = "/clazzStudentDetail"
                        }
                    }
                }
            })
        }

        // 点击发布作业详情触发事件
        function releaseWorkDetail(courseTeacherClazzId) {
            $.ajax({
                url: "/work/releaseWorkDetail",
                type: "post",
                data: JSON.stringify({
                    "courseTeacherClazzId": courseTeacherClazzId
                }),
                contentType: "application/json;charset=utf-8",
                statusCode: {
                    200: function (data) {
                        if (data.indexOf("发布作业详情查询成功") != -1) {
                            window.location = "/releaseWorkDetail"
                        }
                    }
                }
            })
        }
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
        </div>
        <div class="meun-title">当前页面</div>
        <div class="meun-item" id="zygl">作业管理</div>
        <div class="meun-item meun-item-active" id="kcgl">课程管理</div>
        <div class="meun-item" id="bjgl">班级管理</div>
        <div class="meun-title">教师操作</div>
        <#if teacherDto??>
            <div class="meun-title">
                <button class="btn btn-primary" data-toggle="modal" data-target="#chuangjiankechengjiaoshibanji"
                        onclick="addTeacherId('${teacherDto.teacherId}')">
                    创建课程教师班级
                </button>
            </div>
            <div class="meun-title">
                <button class="btn btn-primary" data-toggle="modal" data-target="#chuangjiankecheng">
                    创建课程
                </button>
            </div>
            <div class="meun-title">
                <button class="btn btn-primary" data-toggle="modal" data-target="#chuangjianbanji">
                    创建班级
                </button>
            </div>
            <div class="meun-title">
                <button class="btn btn-danger" data-toggle="modal" data-target="#tuichudenglu">
                    退出登陆
                </button>
            </div>
        </#if>
    </div>

    <div id="rightContent">
        <!--作业管理-->
        <div class="gl zuoyeguanli" style="display:none">
            <div class="cx">
                <div class="input-group">
                    <#if teacherDto??>
                        <input placeholder="输入课程名查询" class="form-control right-ss" id="searchCourseName"/>
                        <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchCourseTeacherClazz">查询</button></span>
                    </#if>
                </div>
            </div>
            <div class="biao">
                <table class="table table-striped table-hover">
                    <colgroup>
                        <col style="width: 16.6%">
                        <col style="width: 16.6%">
                        <col style="width: 16.6%">
                        <col style="width: 16.6%">
                        <col style="width: 16.6%">
                        <col style="">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>课程教师班级id</th>
                        <th>课程名称</th>
                        <th>班级名称</th>
                        <th>发布作业个数</th>
                        <th>创建日期</th>
                        <#if teacherDto??>
                            <th>操作</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                    <#if courseTeacherClazzDtos??>
                        <#list courseTeacherClazzDtos as courseTeacherClazzDto>
                            <tr>
                                <td>${courseTeacherClazzDto.courseTeacherClazzId}</td>
                                <td>${courseTeacherClazzDto.courseName}</td>
                                <td>${courseTeacherClazzDto.clazzName}</td>
                                <td>${courseTeacherClazzDto.workCount}</td>
                                <td>${courseTeacherClazzDto.createTime?string("yyyy-MM-dd")}</td>
                                <#if teacherDto??>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="releaseWorkDetail('${courseTeacherClazzDto.courseTeacherClazzId}')">
                                            发布作业详情
                                        </button>
                                        <button class="btn btn-danger" onclick="addCourseTeacherClazzId
                                                ('${courseTeacherClazzDto.courseTeacherClazzId}')"
                                                data-toggle="modal"
                                                data-target="#shanchukechengjiaoshibanji">
                                            删除
                                        </button>
                                    </td>
                                </#if>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>

        <#--删除课程教师班级-->
            <div class="modal fade" id="shanchukechengjiaoshibanji" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">警告</h4>
                        </div>
                        <div class="modal-body">
                            <p>确 定 删 除 此 课 程 教 师 班 级 ？</p>
                        </div>
                        <form>
                            <input type="hidden" name="courseTeacherClazzId" id="courseTeacherClazzId">
                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                取消
                            </button>
                            <button type="button" class="btn btn-danger" id="deleteCourseTeacherClazz">
                                删除
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--课程管理-->
        <div class="gl kechengguanli">
            <div class="cx">
                <div class="input-group">
                    <#if teacherDto??>
                        <input placeholder="输入课程名查询" class="form-control right-ss" id="searchCourseName1"/>
                        <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchCourse">查询</button></span>
                    </#if>
                </div>
            </div>
            <div class="biao">
                <table class="table table-striped table-hover">
                    <colgroup>
                        <col style="width: 27%">
                        <col style="width: 27%">
                        <col style="width: 27%">
                        <col style="">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>课程id</th>
                        <th>课程名称</th>
                        <th>创建时间</th>
                        <#if teacherDto??>
                            <th>操作</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                    <#if searchCourseDtos??>
                        <#list searchCourseDtos as courseDto>
                            <tr>
                                <td>${courseDto.courseId}</td>
                                <td>${courseDto.courseName}</td>
                                <td>${courseDto.createTime?string("yyyy-MM-dd")}</td>
                                <#if teacherDto??>
                                    <td>
                                        <button class="btn btn-danger" onclick="addCourseId('${courseDto.courseId}')"
                                                data-toggle="modal"
                                                data-target="#shanchukecheng">
                                            删除
                                        </button>
                                    </td>
                                </#if>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    <#--删除课程-->
        <div class="modal fade" id="shanchukecheng" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">警告</h4>
                    </div>
                    <div class="modal-body">
                        <p>确 定 删 除 此 课 程 ？</p>
                    </div>
                    <form>
                        <input type="hidden" name="courseId" id="courseId">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteCourse">
                            删除
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!--班级管理-->
        <div class="gl banjiguanli" style="display:none">
            <div class="cx">
                <div class="input-group">
                    <#if teacherDto??>
                        <input placeholder="输入班级名查询" class="form-control right-ss" id="searchClazzName"/>
                        <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchClazz">查询</button></span>
                    </#if>
                </div>
            </div>
            <div class="biao">
                <table class="table table-striped table-hover">
                    <colgroup>
                        <col style="width: 20%">
                        <col style="width: 20%">
                        <col style="width: 20%">
                        <col style="width: 20%">
                        <col style="">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>班级id</th>
                        <th>班级名称</th>
                        <th>学生数量</th>
                        <th>创建日期</th>
                        <#if teacherDto??>
                            <th>操作</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                    <#if searchClazzDtos??>
                        <#list searchClazzDtos as clazzDto>
                        <tr>
                            <td>${clazzDto.clazzId}</td>
                            <td>${clazzDto.clazzName}</td>
                            <td>${clazzDto.studentCount}</td>
                            <td>${clazzDto.createTime?string("yyyy-MM-dd")}</td>
                            <#if teacherDto??>
                                <td>
                                    <button class="btn btn-danger" onclick="addClazzId('${clazzDto.clazzId}')"
                                            data-toggle="modal"
                                            data-target="#shanchubanji">
                                        删除
                                    </button>
                                    <button class="btn btn-default"
                                            onclick="studentDetail('${clazzDto.clazzId}')">学生详情
                                    </button>
                                </td>
                            </#if>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        <#--删除班级-->
            <div class="modal fade" id="shanchubanji" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">警告</h4>
                        </div>
                        <div class="modal-body">
                            <p>确 定 删 除 此 班 级 ？</p>
                        </div>
                        <form>
                            <input type="hidden" name="clazzId" id="clazzId">
                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                取消
                            </button>
                            <button type="button" class="btn btn-danger" id="deleteClazz">
                                删除
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#--退出登录-->
        <div class="modal fade" id="tuichudenglu" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">警告</h4>
                    </div>
                    <div class="modal-body">
                        <p>确 定 退 出 登 录 ？</p>
                    </div>
                    <form>
                        <input type="hidden" name="studentId" id="studentId">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-danger" id="logout">
                            确定退出
                        </button>
                    </div>
                </div>
            </div>
        </div>

    <#--创建课程教师班级-->
        <div class="modal fade" id="chuangjiankechengjiaoshibanji" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="main">
                        <h3 class="page-title">创建课程教师班级</h3>
                    <#--创建课程教师班级表单-->
                        <form>
                            <div class="form-container">
                                <div class="form-group">
                                    课程选择
                                    <select class="form-control" id="courseId2" name="courseId2">
                                            <#if courseDtos??>
                                                <option></option>
                                                <#list courseDtos as courseDto>
                                                    <option value="${courseDto.courseId}">
                                                        ${courseDto.courseName}
                                                    </option>
                                                </#list>
                                            </#if>
                                    </select>
                                    <span id="courseId2Error" style="color: red">&nbsp&nbsp*</span>
                                </div>
                                <div class="form-group">
                                    班级选择
                                    <select class="form-control" id="clazzId2" name="clazzId2">
                                            <#if clazzDtos??>
                                                <option></option>
                                                <#list clazzDtos as clazzDto>
                                                    <option value="${clazzDto.clazzId}">
                                                        ${clazzDto.clazzName}
                                                    </option>
                                                </#list>
                                            </#if>
                                    </select>
                                    <span id="clazzId2Error" style="color: red">&nbsp&nbsp*</span>
                                </div>

                                <input type="hidden" name="teacherId" id="teacherId"/>
                            </div>
                        </form>
                    <#--将创建按钮放在表单外，若不这样不能通过js阻塞表单提交-->
                        <div class="form-container">
                            <div class="form-group">
                                <button class="btn btn-primary btn-block form-container"
                                        id="createCourseTeacherClazz">创建
                                </button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    id="closeCreateCourseTeacherClazzWindow">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#--创建课程-->
        <div class="modal fade" id="chuangjiankecheng" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="main">
                        <h3 class="page-title">创建课程</h3>
                    <#--创建课程表单-->
                        <form>
                            <div class="form-container">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="课 程 名 称" name="courseName"
                                           id="courseName"/>
                                    <span id="courseNameError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                            </div>
                        </form>
                    <#--将创建按钮放在表单外，若不这样不能通过js阻塞表单提交-->
                        <div class="form-container">
                            <div class="form-group">
                                <button class="btn btn-primary btn-block form-container" id="createCourse">创建
                                </button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    id="closeCreateCourseWindow">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#--创建班级-->
        <div class="modal fade" id="chuangjianbanji" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="main">
                        <h3 class="page-title">创建班级</h3>
                    <#--创建班级表单-->
                        <form>
                            <div class="form-container">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="班 级 名 称" name="clazzName"
                                           id="clazzName"/>
                                    <span id="clazzNameError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                            </div>
                        </form>
                    <#--将创建按钮放在表单外，若不这样不能通过js阻塞表单提交-->
                        <div class="form-container">
                            <div class="form-group">
                                <button class="btn btn-primary btn-block form-container"
                                        id="createClazz">创建
                                </button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    id="closeCreateClazzWindow">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
