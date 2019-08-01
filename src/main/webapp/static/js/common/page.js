//分页
function paging(url,arg1,agr2,arg3) {
    $('.content').empty();
    $.post(url, {"page": arg1, "limit": agr2,"tag":arg3},
        function (data) {
            $.each(data.data, function (index, value) {
                $('.left').append(
                    "<div class='content'>" +
                    "<a target='blank' href='/haha/app/page?id="+value.id+"'style='font-size: 32px;font-style: normal'>" + value.tittle + "</a>" +
                    "<div style='margin-top: 2%;margin-bottom: 4%'>" +
                    "<span style='font-size: 14px;font-style: normal'>" + value.summary + "</span>" +
                    "<a target='blank' href='/haha/app/page?id="+value.id+"'style='font-size: 12px;font-style: normal;color: blue'>[详情]</a>"+
                    "</div>" +
                    "<div style='font-size: 14px;font-style: normal'>" +
                    "<div class='div-inline'> <i  class='layui-icon'>&#xe637;</i><span>" + value.createDate + "</span></div>" +
                    "<div class='div-inline' style='margin-left: 5%'><i class='layui-icon'>&#xe66e;</i><span >" + value.categoryName + "</span></div>" +
                    "</div>"+
                    "</div>"
                );
            })
            $('.content').insertBefore($('#test1'))
            var scroll_top = window.parent.document.scrollingElement;
            $(scroll_top).animate({
                scrollTop : '0px'
            },500);
        }, "json");
}