$(function () {
    //跳出iframe
    if (top.location !== self.location) {
        top.location.href = self.location.href;
    }
    layui.use('form', function () {
           var form = layui.form
        form.verify({
            /**
             * 匹配任何非空白字符
             */
            tittle: [/^[\S]{4,9}$/,'标题必须4-8位，且不能出现空格'],
            summary:[/^[\S]{10,31}$/,'摘要必须10-30位，且不能出现空格']

    })
    })
})
