$(function () {
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.jquery;
        //模糊查询
        $('#find').on('click', function () {
            var mes = $('#search').val();
            $('.layui-tab-item').html("<iframe src='/haha/blog/findBlog?mes=" +mes+"' scrolling='auto' frameborder='0' height='600' width='100%'></iframe>")
        })
        //刷新
        $('#refresh').on('click',function () {
            window.parent.location.reload(true);
        })
        //数据表格
        var active = {
            tabAdd: function (url, id, name) {
                element.tabAdd('docDemoTabBrief', {
                    title: name,
                    content: '<iframe  data-frameid="' + id + '" src="' + url + '" id="main" name="main" scrolling="auto" frameborder="0" height="600" width="100%"></iframe>',
                    id: id
                })
                element.render('tab');
            },
            tabChange: function (id) {
                element.tabChange('docDemoTabBrief', id);
            },
            tabDelete: function (id) {
                element.tabDelete("docDemoTabBrief", id);
            }
        };
        $('.site-demo-active').on('click', function () {
            var dataId = $(this);
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("data-title"));
            } else {
                var isData = false;
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    if ($(this).attr("lay-id") == dataId.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("data-title"));
                }
            }
            active.tabChange(dataId.attr("data-id"));
        })
    })
})