$(function() {
    $("#sure").click(function () {
        var number = $("#guess_number").val();
        var id = $("#id").val();
        if(id == null || id == "") {
            layer.msg("系统未生成随机数，请点击生成");
            return;
        }
        var data = {id: id, number: number};
        $.ajax({
            url: path + "/fun/game/guess/guess.json",
            data: data,
            success: function(res) {
                if (isNaN(res)) {
                    layer.msg("系统错误");
                    return
                }
                if (res == 2) {
                    layer.confirm('随机数已不存在，请重新生成', {
                        btn: ['刷新', '取消']
                    }, function() {

                        $("#random").click();

                    }, function () {
                    });
                    return;
                }
                if (res == 1) {
                    layer.msg("猜小了");
                }
                if (res == 0) {
                    layer.msg("猜中了");
                }
                if (res == -1) {
                    layer.msg("猜大了");
                }
            }
        })
    });

    $("#random").click(function () {
        $.ajax({
            url: path + "/fun/game/guess/random.json",
            success: function(res) {
                $("#id").val(res);
                layer.msg("系统已生成100以内的随机数");
            }
        })
    });
});