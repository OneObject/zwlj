<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/5/17 0017
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<html>
<head>
    <title>YY</title>
</head>
<body>
<div>
    <span class="button">点击</span>
</div>
<script src="${basepath}/static/component/jquery/jquery-3.2.1.min.js"></script>
<script src="${basepath}/static/component/layer/layer.js"></script>
<script>
    $(function() {
        var num = 0;
        $(".button").click(function() {
            switch (num) {
                case 0:
                    layer.msg("点击了，哈哈");
                    break;
                case 1:
                    layer.alert("点击了");
                    break;
                case 2:
                    var index = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                    break;
                default:
                    layer.closeAll();
            }
            num++;
        });
    })
</script>
</body>
</html>
