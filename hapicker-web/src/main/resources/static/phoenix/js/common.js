/*开启关闭音乐*/
    $('#yinfu').on('click', function() {
        var audio = document.getElementById('music1');
        if (audio !== null) {
            if (audio.paused) {
                $(this).addClass('rotate');
                $('#audio_btn').addClass('yinfugif');
                audio.play();
            } else {
                $(this).removeClass('rotate');
                $('#audio_btn').removeClass('yinfugif');
                audio.pause();
            }
        }
    })

    // var isjiazai;
// window.onload = function() {
//     yujiazai();
// }

// function yujiazai() {
//     $('#loadwz span').text(1);
//     isjiazai = 0;
//     var load_img = [];
//     $('.loadimg').each(function() {
//         var loadimg = $(this).attr('loadimg');
//         load_img.push(loadimg);
//     })
//     setTimeout(function() {
//         if (isjiazai == 0) {
//             alert('加载资源失败,请尝试关闭微信后重新打开页面');
//         }
//     }, 8000)
//     window.imgNum = 0;
//     /*ss*/
//     jQuery.imgpreload(load_img, {
//         each: function() {
//             /*this will be called after each image loaded*/
//             var status = $(this).data('loaded') ? 'success' : 'error';
//             console.log(status)
//             if (status == "success") {
//                 var v = (parseFloat(++imgNum) / load_img.length).toFixed(2);
//                 $('#loadwz span').text(Math.round(v * 100));
//                 console.log(Math.round(v * 100) + "<sup>%</sup>");
//                 isjiazai = 1;
//             } else {
//                 console.log('fwe');
//             }
//         },
//         all: function() {
//             console.log('全部加载完成');
//             $('.loadimg').each(function() {
//                 var loadimg = $(this).attr('loadimg');
//                 $(this).attr('src', loadimg);
//             })
//             setTimeout(function() {
//                 $('.yujiazai').css('display', 'none');
//                 $('#star').fadeIn();
//                 swipperinit();
//                 shareinit();
//             }, 0)
//         }
//     });
// }
// function swipperinit(){
//     var mySwiper = $('.swiper-container').swiper({
//         mode: 'vertical',
//         loop: false,
//         speed: 500,
//         onSlideChangeEnd: function() {
//             $(".swiper-slide").removeClass("on");
//             setTimeout(function() {
//                 $(".swiper-slide-active").addClass("on");
//             }, 300)
//             if (!$(".swiper-slide-active").next().length)
//             {
//                 $(".up").hide();
//             }
//             else
//             {
//                 $(".up").show();
//             }
//         }
//     })
//     $(".loading").addClass("loadingremove");
//     setTimeout(function() {
//         $(".swiper-slide-active").addClass("on");
//     }, 300)
//     $(".up").show();
//     //mySwiper.swipeTo(8);
//    /* $(".sound")[0].addEventListener('touchstart', function(event) {
//         if ($(this).hasClass("soundstop"))
//         {
//             document.getElementById("music").play();
//             $(this).removeClass("soundstop");
//         }
//         else
//         {
//             document.getElementById("music").pause();
//             $(this).addClass("soundstop");
//         }
//     })*/
//     $(".ipt").each(function() {
//         var obj = $(this);
//         this.addEventListener('touchstart', function(event) {
//             autofocus = setTimeout(function() {
//                 obj.focus();
//             }, 300)
//         })
//     })
// }

// function shareinit() {
//     $('#yinfu').addClass('rotate');
//     $('#audio_btn').addClass('yinfugif');
//     $('#music1')[0].play();
    // wx.config({
    //     //debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    //     appId: $('#appId').val(), // 必填，公众号的唯一标识
    //     timestamp: $('#timestamp').val(), // 必填，生成签名的时间戳
    //     nonceStr: $('#nonceStr').val(), // 必填，生成签名的随机串
    //     signature: $('#signature').val(), // 必填，签名，见附录1
    //     jsApiList: ['checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem', 'translateVoice', 'startRecord', 'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView', 'addCard', 'chooseCard', 'openCard']
    // });
    // var ntitle = $('#ntitle').val();
    // var ndesc = $('#ndesc').val();
    // var nlogo = $('#nlogo').val();
    // var link = $('#link').val();
    // wx.ready(function() {
        
    //     wx.checkJsApi({
    //         jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline'],
    //         success: function(res) {
    //             //alert(JSON.stringify(res));
    //         }
    //     });
    //     wx.onMenuShareAppMessage({
    //         title: ntitle,
    //         desc: ndesc,
    //         link: link,
    //         imgUrl: nlogo,
    //         trigger: function(res) {
    //             //alert("点击分享：" +JSON.stringify(res));
    //         },
    //         success: function(res) {
    //             //    window.location.href = "http://wxserver.knowway.cn/home/haiyang/result";
    //         },
    //         cancel: function(res) {},
    //         fail: function(res) {}
    //     });
    //     wx.onMenuShareTimeline({
    //         title: ntitle,
    //         desc: ndesc,
    //         link: link,
    //         imgUrl: nlogo,
    //         trigger: function(res) {},
    //         success: function(res) {
    //             //    window.location.href = "http://wxserver.knowway.cn/home/haiyang/result";
    //         },
    //         cancel: function(res) {},
    //         fail: function(res) {}
    //     });
    //     /*
    //      wx.onMenuShareQQ({
    //      title: ntitle,
    //      desc: ndesc,
    //      link: link,
    //      imgUrl: nlogo,
    //      success: function () {
    //      // 用户确认分享后执行的回调函数
    //      },
    //      cancel: function () {
    //      // 用户取消分享后执行的回调函数
    //      }
    //      });
    //      */
    // });
// }
