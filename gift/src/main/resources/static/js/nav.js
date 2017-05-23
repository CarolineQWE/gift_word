/**
 * Created by fyq on 2017/5/14.
 */
$('.strategy_li').hover(function(){
    $(this).find('.strategy_menu').css('display','block');
},function(){
    $(this).find('.strategy_menu').css('display','none');
});
$(".selector_li").hover(function(){
    $(this).children('.con').css('display','block');
    $(this).addClass('li_border');
},function(){
    $(this).children('.con').css('display','none');
    $(this).removeClass('li_border');
})

/*var $window = $(window);
var $div_anchor = $('.container');
var $nav_bar = $('.nav_bar');
var $fixed_nav_bar = $('.fixed_nav_bar');
$window.scroll(function() {
    //scrollTop()方法返回或设置匹配元素的滚动条的垂直位置
    var window_top = $window.scrollTop();
    //javascript用offsetTop();jquery用offset().top;
    var div_top = $div_anchor.offset().top;
    if(window_top > div_top) {
        $nav_bar.css('display', 'none');
        $fixed_nav_bar.css('display', 'block');
    } else {
        $nav_bar.css('display', 'block');
        $fixed_nav_bar.css('display', 'none');
    }
});*/
$("#search_btn").click(function () {
    console.log("进入搜索方法");
    var keyword = $("#search").val();
    if(!(keyword==null||keyword == "")){
        var url = "/search/Keyword";
        var obj = {
            name:"keyword",
            value:keyword
        };
        formSubmit(url,obj);
    }
})

$('.white_img_outer').click(function () {
    var keyword = $("#white_input").val();
    if(!(keyword==null||keyword == "")){
        var url = "/search/Keyword";
        var obj = {
            name:"keyword",
            value:keyword
        };
        formSubmit(url,obj);
    }
})