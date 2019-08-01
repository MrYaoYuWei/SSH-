$(function () {
    countDown(5);
})

function countDown(secs)
{
    var $counter = document.getElementById('counter');
    $counter.innerText=secs;
    if(--secs>0)
    {
        setTimeout("countDown("+secs+")",1000);
    }
    if(secs==0)
    {
        location.href = '/haha/index.html';
    }
}