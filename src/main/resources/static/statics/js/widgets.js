function createPage(pageInfo) {
    var currentPage = pageInfo.currentPage;    //当前页
    var totalPages = pageInfo.totalPages;     //总页数
//console.log(totalPages);
    if (totalPages > 1) {
        var pagination = '';      //分页标签
        var flag = totalPages > 8 ? true : false;           //大于8页只显示8页，flag用来记录，后面通过flag判断是否大于8页(区分本来就只有8页)
        if(totalPages > 8){
            totalPages=8;
        }
        for (var i = 1; i <= totalPages; i++) {     //拼装
            if (i == currentPage) {                 //当前页加上样式区分
                pagination += '<a class="current" href="index?page=' + i + '">' + i + '</a>';
            } else {
                pagination += '<a href="index?page=' + i + '">' + i + '</a>';
            }
        }
        if (flag) {
            pagination += '<a href="#">...</a>';    //大于8页，显示省略号==
        }
    }
    $('#pagination').html(pagination);
}



function isPC() {
    var userDevice = navigator.userAgent;
    var agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    for (var i = 0; i < agents.length; i++) {
        if (userDevice.indexOf(agents[i]) > -1) {
            return false;
        }
    }
    return true;
}

document.addEventListener('scroll', function () {
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    var top = $('.scroll-top');
    if (scrollTop > 100) {
        top.css({'opacity': '1', 'transform': 'translate(0, 0)'});

    } else {
        top.css({'opacity': '0', 'transform': 'translate(0, 0)'});
        top.removeClass('opacity')
    }
});

$('.scroll-top').on('click', function () {
    $('html, body').animate({
        scrollTop: 0
    }, 600)
});
