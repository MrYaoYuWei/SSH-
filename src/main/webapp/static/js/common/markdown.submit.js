$(function () {
    layui.use(['form', 'element', 'layer'], function () {
        var form = layui.form;
        var element = layui.element;
        var layer = layui.layer;
        form.on('submit(post)', function () {
            var tittle = $('#tittle').val();
            var summary = $('#summary').val();
            var category = $("#category option:selected").val();
            var content = $('#content').val();
            $.post("/haha/blog/saveBlog", {
                "tittle": tittle,
                "summary": summary,
                "category": category,
                "content": content
            }, function (data) {
                if (data.status == 0) {
                    layer.msg("发布成功", {offset: '200px', time: 1000});
                    $(location).prop('href', "/haha/app/admin");
                }
                else {
                    layer.msg("发布成功", {offset: '200px', time: 1000});
                    $(location).prop('href', "/haha/app/admin");
                }
            }, "json")
            return false;
        })
        form.on('submit(update)', function (data) {
            var id = $('#id').val();
            var tittle = $('#tittle').val();
            var summary = $('#summary').val();
            var category = $("#category option:selected").val();
            var content = $('#content').val();
            $.post("/haha/blog/saveBlog", {
                "id": id,
                "tittle": tittle,
                "summary": summary,
                "category": category,
                "content": content
            }, function (data) {
                if (data.status == 0) {
                    layer.msg("修改成功", {offset: '200px', time: 1000});
                    $(location).prop('href', "/haha/app/admin");
                }
                else {
                    layer.msg("修改不成功", {offset: '200px', time: 1000});
                    $(location).prop('href', "/haha/app/admin");
                }
            }, "json")
            return false;
        })
    })
})