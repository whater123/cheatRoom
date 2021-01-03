$(function () {
   // 侧边栏弹出
   $('#b1').click(function () {
      var left = $('#wrapper')[0].offsetLeft;
      if (left == 0) {
         $('#wrapper').offset({
            'left': 220
         });
         $(this).css('transform', 'rotate(450deg)');
         $('.headSculpture img').addClass('img');
         $('.headSculpture p').addClass('opacity');
         setTimeout(function () {
            $('.option ul>li').addClass('li');
         }, 600)
      } else {
         $('#wrapper').offset({
            'left': 0
         });
         $(this).css('transform', 'rotate(0deg)');
         setTimeout(function () {
            $('.headSculpture img').removeClass('img');
            $('.headSculpture p').removeClass('opacity');
            $('.option ul>li').removeClass('li');
         }, 300)
      }
   })

   // 鼠标触碰改变img样式
   var SRC = [{
         src: '../static/images/home_1.png'
      },
      {
         src: '../static/images/works_1.png'
      },
      {
         src: '../static/images/release_1.png'
      },
      {
         src: '../static/images/collection_1.png'
      },
      {
         src: '../static/images/setup_1.png'
      },
      {
         src: '../static/images/home.png'
      },
      {
         src: '../static/images/works.png'
      },
      {
         src: '../static/images/release.png'
      },
      {
         src: '../static/images/collection.png'
      },
      {
         src: '../static/images/setup.png'
      },
   ]
   $('.option ul>li').mouseenter(function () {
      var index = $(this).index();
      $(this).children('img').attr('src', SRC[index].src);
   })
   $('.option ul>li').mouseleave(function () {
      var index = $(this).index();
      $(this).children('img').attr('src', SRC[index + 5].src);
   })
})