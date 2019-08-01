$(function () {
    var scroll_top = window.parent.document.scrollingElement;
    $('.scroll').on('click', function () {
        $(scroll_top).animate({
            scrollTop: '0px'
        }, 500);
    })
})