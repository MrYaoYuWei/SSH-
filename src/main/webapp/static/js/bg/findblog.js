$(function () {
    layui.use('table', function () {
        var table = layui.table;
        table.on('tool(blogList)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if (layEvent === 'detail') { //查看
                //do somehing 跳转内容页面传id
                $(location).prop('href', '/haha/app/page?id=' + data.id);
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);//向服务端发送删除指令传id
                    $.post("/haha/blog/deleteBlog", {"id": data.id}, function (message) {
                        //显示返回信息
                        layer.msg(message.msg, {offset: '200px', time: 1000});
                    }, "json")
                });
            } else if (layEvent === 'edit') {
                //编辑博客信息
                $(location).prop('href', '/haha/blog/writeBlog?id=' + data.id);
            }
        })
    })
})

