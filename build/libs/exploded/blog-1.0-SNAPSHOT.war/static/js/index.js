$(function () {
    layui.use('element', function () {
        var element = layui.element;
        $('.layui-btn').on('click', function () {
            var tag = $('#search').val();
            $('.layui-tab-item').html("<iframe src='/haha/app/iframe?tag=" + tag + "'width='100%' height='1600px' frameborder='0' scrolling='no'></iframe>")
        })
        element.on('tab(docDemoTabBrief)', function (data) {
            if (data.index == 0) {
                $('.layui-tab-item').html("<iframe src='/haha/app/iframe?tag=top' width='100%' height='1500px' frameborder='0' scrolling='no' id='top'></iframe>")
            }
            else if (data.index == 1) {
                $('.layui-tab-item').html('<iframe src="/haha/app/iframe?tag=technology" width="100%" height="1600px" frameborder="0" scrolling="no" id="technology"></iframe>')

            }
            else if (data.index == 2) {
                $('.layui-tab-item').html(' <iframe src="/haha/app/iframe?tag=mood" width="100%" height="1600px" frameborder="0" scrolling="no"id="mood"></iframe>')
            }
            else if (data.index == 3) {
                $('.layui-tab-item').html(' <iframe src="/haha/app/iframe?tag=insight" width="100%" height="1600px" frameborder="0" scrolling="no" id="insight"></iframe>')
            } else if (data.index == 4) {
                $('.layui-tab-item').html(' <iframe src="/haha/app/iframe?tag=memory" width="100%" height="1600px" frameborder="0" scrolling="no" id="memory"></iframe>')

            } else if (data.index == 5) {
                $('.layui-tab-item').html('<iframe src="/haha/app/iframe?tag=book" width="100%" height="1600px" frameborder="0" scrolling="no" id="book"></iframe>')
            }
        })
    })
})