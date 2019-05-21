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

        .form-container-wide{
            width: 600px;
            margin: 0 auto;
        }

        .form-container-narrow{
            width: 100px;
            margin: 0 auto;
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
            $("#tjzyxq").click(function () {
                $(".gl").attr("style", "display:none");
                $(".tijiaozuoyexiangqing").attr("style", "display:block");
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
            $("#closeSaveScoreWindow").click(function () {
                $("#submitWorkScoreError").html("&nbsp&nbsp*")
            })
            // 搜索提交作业
            $("#searchSubmitWork").click(function () {
                $.ajax({
                    url: "/submitWork/getSubmitWorkBySearch",
                    type: "post",
                    data: JSON.stringify({
                        "searchStudentName": $("#searchStudentName").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.indexOf("查询成功") != -1) {
                                window.location = "/submitWorkDetail"
                            }
                        }
                    }
                })
            })
        })
        // 回到发布作业详情页面
        function goBackReleaseWorkDetail(courseTeacherClazzId) {
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
        // 添加提交作业信息事件
        function addSubmitWorkInfo(submitWorkId, workId) {
            $.ajax({
                url: "/submitWork/querySubmitWorkContentAndScore",
                type: "post",
                data: JSON.stringify({
                    "submitWorkId": submitWorkId
                }),
                contentType: "application/json;charset=utf-8",
                statusCode: {
                    200: function (data) {
                        var datas = data.split("+_+")
                        $("#submitWorkId").val(submitWorkId)
                        $("#workId").val(workId)
                        $("#submitWorkContent").val(datas[0])
                        $("#submitWorkScore").val(datas[1])
                    }
                }
            })
        }
        // 保存分数
        function saveScore() {
            var score = $("#submitWorkScore").val()
            if (score == "") {
                $("#submitWorkScoreError").html("分 数 不 能 为 空 !")
                return
            } else if (isNaN(parseInt(score))) {
                $("#submitWorkScoreError").html("分 数 只 能 为 数 字 !")
                return
            } else if (parseInt(score) > 100) {
                $("#submitWorkScoreError").html("最 高 分 数 只 能 为 100 分 !")
                return
            } else {
                $("#submitWorkScoreError").html("*")
            }
            $.ajax({
                url: "/submitWork/saveScore",
                type: "post",
                data: JSON.stringify({
                    "submitWorkId": $("#submitWorkId").val(),
                    "score": $("#submitWorkScore").val()
                }),
                contentType: "application/json;charset=utf-8",
                statusCode: {
                    200: function (data) {
                        if (data.indexOf("保存成功") != -1) {
                            alert("保存成功")
                            $.ajax({
                                url: "/submitWork/submitWorkDetail",
                                type: "post",
                                data: JSON.stringify({
                                    "workId": $("#workId").val()
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
                        } else {
                            alert("保存失败")
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
            <p>
                <#if teacherDto??>
                    <button class="btn btn-danger" id="logout">退出登陆</button>
                </#if>
            </p>
        </div>
        <div class="meun-title">教师操作</div>
        <div class="meun-item meun-item-active" id="tjzyxq">提交作业详情</div>
    </div>
    <div id="rightContent">
        <!--提交作业详情-->
        <div class="gl tijiaozuoyexiangqing">
            <div class="cx">
                <div class="input-group">
                    <input placeholder="输入学生姓名查询" class="form-control right-ss" id="searchStudentName"/>
                    <span class="input-group-btn"><button class="btn btn-default right-ss" id="searchSubmitWork">查询</button></span>
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
                        <th>提交作业id</th>
                        <th>提交作业内容</th>
                        <th>最高重复率</th>
                        <th>学生姓名</th>
                        <th>得分</th>
                        <th>创建日期</th>
                        <#if teacherDto??>
                            <th>操作</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                        <#if submitWorkDtos??>
                            <#list submitWorkDtos as submitWorkDto>
                                <tr>
                                    <td>${submitWorkDto.submitWorkId}</td>
                                    <td>${submitWorkDto.submitWorkContent}</td>
                                    <td>${submitWorkDto.highRepetitiveRate?string.percent}</td>
                                    <td>${submitWorkDto.studentName}</td>
                                    <td>
                                        <#if submitWorkDto.score??>
                                            ${submitWorkDto.score}
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                    <td>${submitWorkDto.createTime?string("yyyy-MM-dd")}</td>
                                    <td>
                                        <#if teacherDto??>
                                            <button class="btn btn-primary"
                                                    data-toggle="modal"
                                                    onclick="addSubmitWorkInfo('${submitWorkDto.submitWorkId}','${submitWorkDto.workId}')"
                                                    data-target="#pigaizuoye">
                                                批改作业
                                            </button>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    <#--批改作业-->
        <div class="modal fade" id="pigaizuoye" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="main">
                        <h3 class="page-title">批改作业</h3>
                    <#--提交作业表单-->
                        <form>
                            <div class="form-container-wide">
                                <div class="form-group">
                                    提交作业内容:
                                    <textarea disabled rows="20" cols="50" class="form-control"
                                              id="submitWorkContent"></textarea>
                                </div>
                                <input type="hidden" name="submitWorkId" id="submitWorkId"/>
                                <input type="hidden" name="workId" id="workId"/>
                            </div>
                            <div class="form-container-narrow">
                                <div class="form-group">
                                    得分：
                                    <input type="text" class="form-control" id="submitWorkScore"/>
                                    <span id="submitWorkScoreError" style="color: red">&nbsp&nbsp*</span>
                                </div>
                            </div>
                        </form>
                    <#--将保存按钮放在表单外，若不这样不能通过js阻塞表单提交-->
                        <div class="form-container">
                            <div class="form-group">
                                <button class="btn btn-primary btn-block form-container" onclick="saveScore()">
                                    保存
                                </button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    id="closeSaveScoreWindow">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="dao">
            <nav aria-label="Page navigation">
                <button class="btn btn-primary" id="goBackWorkManagement">
                    主页面
                </button>

                <button class="btn btn-primary" onclick="goBackReleaseWorkDetail('${courseTeacherClazzId}')">
                    发布作业页面
                </button>
            </nav>
        </div>
    </div>
</div>
</body>
</html>