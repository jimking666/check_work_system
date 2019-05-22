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
            $("#fbzyxq").click(function () {
                $(".gl").attr("style", "display:none");
                $(".fabuzuoyexiangqing").attr("style", "display:block");
            })
            // 回到作业管理页面
            $("#goBackWorkManagement").click(function () {
                window.location = "/workManagement"
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

            // 搜索作业
            $("#searchWork").click(function () {
                $.ajax({
                    url: "/work/getWorkBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchWorkTitle": $("#searchWorkTitle").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/releaseWorkDetail"
                            }
                        }
                    }
                })
            })

            // 点击发布触发事件
            $("#createWork").click(function () {
                if ($("#workTitle").val() == "" || $("#workContent").val() == "" || $("#repetitiveRate").val() == "") {
                    // 若作业题目为空
                    if ($("#workTitle").val() == "") {
                        $("#workTitleError").html("&nbsp&nbsp作 业 题 目 不 能 为 空 !")
                    } else {
                        $("#workTitleError").html("&nbsp&nbsp*")
                    }
                    // 若作业内容为空
                    if ($("#workContent").val() == "") {
                        $("#workContentError").html("&nbsp&nbsp作 业 内 容 不 能 为 空 !")
                    } else {
                        $("#workContentError").html("&nbsp&nbsp*")
                    }
                    // 若重复率为空
                    if ($("#repetitiveRate").val() == "") {
                        $("#repetitiveRateError").html("&nbsp&nbsp重 复 率 不 能 为 空 !")
                    } else {
                        $("#repetitiveRateError").html("&nbsp&nbsp*")
                    }
                    return
                } else {
                    $.ajax({
                        url: "/work/create",
                        type: "post",
                        data: JSON.stringify({
                            "workTitle": $("#workTitle").val(),
                            "workContent": $("#workContent").val(),
                            "repetitiveRate": $("#repetitiveRate").val(),
                            "courseTeacherClazzId": $("#courseTeacherClazzId").val()
                        }),
                        contentType: "application/json;charset=utf-8",
                        statusCode: {
                            200: function (data) {
                                if (data.indexOf("发布成功") != -1) {
                                    alert("发 布 成 功 !")
                                    $.ajax({
                                        url: "/work/releaseWorkDetail",
                                        type: "post",
                                        data: JSON.stringify({
                                            "courseTeacherClazzId": $("#courseTeacherClazzId").val()
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
                                } else if (data.indexOf("重复发布") != -1) {
                                    $("#workTitleError").html("&nbsp&nbsp*")
                                    $("#workContentError").html("&nbsp&nbsp*")
                                    $("#repetitiveRateError").html("&nbsp&nbsp已 存 在 此 作 业 !")
                                }
                            }
                        }
                    })
                }
            })

            // 点击作业确认删除触发事件
            $("#deleteWork").click(function () {
                $.ajax({
                    url: "/work/delete",
                    type: "post",
                    data: JSON.stringify({
                        "workId": $("#workId").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("删除成功") != -1) {
                                alert("删 除 成 功 !")
                                $.ajax({
                                    url: "/work/releaseWorkDetail",
                                    type: "post",
                                    data: JSON.stringify({
                                        "courseTeacherClazzId": $("#courseTeacherClazzId1").val()
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
                        }
                    }
                })
            })

            // 关闭发布作业窗口
            $("#closeCreateWorkWindow").click(function () {
                $("#workTitleError").html("&nbsp&nbsp*")
                $("#workTitle").val("")
                $("#workContentError").html("&nbsp&nbsp*")
                $("#workContent").val("")
                $("#repetitiveRateError").html("&nbsp&nbsp*")
                $("#repetitiveRate").val("")
            })
        })

        // 点击发布作业触发事件
        function addCourseTeacherClazzId(courseTeacherClazzId) {
            $("#courseTeacherClazzId").val(courseTeacherClazzId)
        }

        // 点击提交作业详情触发事件
        function submitWorkDetail(workId) {
            $.ajax({
                url: "/submitWork/submitWorkDetail",
                type: "post",
                data: JSON.stringify({
                    "workId": workId
                }),
                contentType: "application/json;charset=utf-8",
                statusCode: {
                    200: function (data) {
                        if (data.indexOf("提交作业详情查询成功") != -1) {
                            window.location = "/submitWorkDetail"
                        }
                    }
                }
            })
        }

        // 点击作业删除触发事件
        function addWorkId(workId, courseTeacherClazzId1) {
            $("#workId").val(workId)
            $("#courseTeacherClazzId1").val(courseTeacherClazzId1)
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
        <div class="meun-item meun-item-active" id="fbzyxq">发布作业详情</div>
        <div class="meun-title">教师操作</div>
        <#if teacherDto??>
            <div class="meun-title">
                <button class="btn btn-primary" data-toggle="modal" data-target="#fabuzuoye"
                        onclick="addCourseTeacherClazzId('${courseTeacherClazzId}')">
                    发布作业
                </button>
            </div>
            <div class="meun-item">
                <button class="btn btn-primary" id="goBackWorkManagement">
                    主页面
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
        <!--发布作业详情-->
        <div class="gl fabuzuoyexiangqing">
            <div class="cx">
                <div class="input-group">
                    <#if teacherDto??>
                        <input placeholder="输入作业题目名称查询" class="form-control right-ss" id="searchWorkTitle"/>
                        <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchWork">查询</button></span>
                    </#if>
                </div>
            </div>
            <div class="biao">
                <table class="table table-striped">
                    <colgroup>
                        <col style="width: 14%">
                        <col style="width: 14%">
                        <col style="width: 14%">
                        <col style="width: 14%">
                        <col style="width: 14%">
                        <col style="width: 14%">
                        <col style="">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>作业id</th>
                        <th>作业题目</th>
                        <th>作业内容</th>
                        <th>重复率</th>
                        <th>提交作业个数</th>
                        <th>创建日期</th>
                        <#if teacherDto??>
                            <th>操作</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                        <#if workDtos??>
                            <#list workDtos as workDto>
                                <tr>
                                    <td>${workDto.workId}</td>
                                    <td>${workDto.workTitle}</td>
                                    <td>${workDto.workContent}</td>
                                    <td>${workDto.repetitiveRate?string.percent}</td>
                                    <td>${workDto.submitWorkCount}</td>
                                    <td>${workDto.createTime?string("yyyy-MM-dd")}</td>
                                    <#if teacherDto??>
                                        <td>
                                            <button class="btn btn-primary" onclick="submitWorkDetail('${workDto.workId}')">
                                                提交作业详情
                                            </button>
                                            <button class="btn btn-danger"
                                                    onclick="addWorkId('${workDto.workId}','${courseTeacherClazzId}')"
                                                    data-toggle="modal"
                                                    data-target="#shanchuzuoye">
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

    <#--发布作业-->
        <div class="modal fade" id="fabuzuoye" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="main">
                        <h3 class="page-title">发布作业</h3>
                    <#--发布作业表单-->
                        <form>
                            <div class="form-container">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="作业题目" name="workTitle"
                                           id="workTitle"/>
                                    <span id="workTitleError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                                <div class="form-group">
                                    <textarea rows="10" cols="30" class="form-control" placeholder="作业内容"
                                              name="workContent"
                                              id="workContent"></textarea>
                                    <span id="workContentError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                                <div class="form-group">
                                    重复率
                                    <select class="form-control" id="repetitiveRate" name="repetitiveRate">
                                        <option></option>
                                        <option value="0.0">
                                            0%
                                        </option>
                                        <option value="0.1">
                                            10%
                                        </option>
                                        <option value="0.2">
                                            20%
                                        </option>
                                        <option value="0.3">
                                            30%
                                        </option>
                                        <option value="0.4">
                                            40%
                                        </option>
                                        <option value="0.5">
                                            50%
                                        </option>
                                        <option value="0.6">
                                            60%
                                        </option>
                                        <option value="0.7">
                                            70%
                                        </option>
                                        <option value="0.8">
                                            80%
                                        </option>
                                        <option value="0.9">
                                            90%
                                        </option>
                                        <option value="1.0">
                                            100%
                                        </option>
                                    </select>
                                    <span id="repetitiveRateError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                                <input type="hidden" name="courseTeacherClazzId" id="courseTeacherClazzId"/>
                            </div>
                        </form>
                    <#--将创建按钮放在表单外，若不这样不能通过js阻塞表单提交-->
                        <div class="form-container">
                            <div class="form-group">
                                <button class="btn btn-primary btn-block form-container" id="createWork">发布</button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    id="closeCreateWorkWindow">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#--删除作业-->
        <div class="modal fade" id="shanchuzuoye" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">警告</h4>
                    </div>
                    <div class="modal-body">
                        <p>确 定 删 除 此 作 业 ？</p>
                    </div>
                    <form>
                        <input type="hidden" name="workId" id="workId">
                        <input type="hidden" name="courseTeacherClazzId1" id="courseTeacherClazzId1">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteWork">
                            删除
                        </button>
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
    </div>
</div>
</body>
</html>