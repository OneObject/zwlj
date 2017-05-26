<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/5/22 0022
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<html>
<head>
    <title>Guess Number</title>
    <link rel="stylesheet" href="${path}/static/public/css/reset.css">
    <link rel="stylesheet" href="${path}/static/component/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/static/fun/game/css/style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <h1 class="text-center">猜数字</h1>
    </div>
    <div class="row guess">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="input-group">
                <input type="number" class="form-control" id="guess_number" placeholder="请输入猜测的数字">
                <span class="input-group-btn">
                    <button id="sure" class="btn btn-info" type="button">确定</button>
                  </span>
            </div>
        </div>

        <input type="hidden" id="id">
    </div>
    <div class="row text-center">
        <button id="random" class="btn btn-info ">生成随机数</button>
    </div>
</div>

<script src="${path}/static/component/jquery/jquery-3.2.1.min.js"></script>
<script src="${path}/static/component/layer/layer.js"></script>
<script src="${path}/static/fun/game/js/guess.js"> </script>
</body>
</html>
