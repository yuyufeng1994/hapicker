// JavaScript Document
$(function() {
    //婊氬姩浜嬩欢缁戝畾
    // var mySwiper = $('.swiper-container').swiper({
    //     mode: 'vertical',
    //     loop: false,
    //     speed: 500,
    //     onSlideChangeEnd: function() {
    //         $(".swiper-slide").removeClass("on");
    //         setTimeout(function() {
    //             $(".swiper-slide-active").addClass("on");
    //         }, 300)
    //         if (!$(".swiper-slide-active").next().length)
    //         {
    //             $(".up").hide();
    //         }
    //         else
    //         {
    //             $(".up").show();
    //         }
    //     }
    // })
    // $(".loading").addClass("loadingremove");
    // setTimeout(function() {
    //     $(".swiper-slide-active").addClass("on");
    // }, 300)
    // $(".up").show();
    //mySwiper.swipeTo(8);
   /* $(".sound")[0].addEventListener('touchstart', function(event) {
        if ($(this).hasClass("soundstop"))
        {
            document.getElementById("music").play();
            $(this).removeClass("soundstop");
        }
        else
        {
            document.getElementById("music").pause();
            $(this).addClass("soundstop");
        }
    })*/
    // $(".ipt").each(function() {
    //     var obj = $(this);
    //     this.addEventListener('touchstart', function(event) {
    //         autofocus = setTimeout(function() {
    //             obj.focus();
    //         }, 300)
    //     })
    // })

//    var top = cha = y = 0;
//    $(".input").each(function() {
//        this.addEventListener('touchstart', function(event) {
//            y = event.targetTouches[0].pageY;
//            cha = y - this.offsetTop;
//        })
//        this.addEventListener('touchmove', function(event) {
//            clearTimeout(autofocus);
//            if ($(".ipt").is(":focus"))
//            {
//                this.style.marginTop = event.targetTouches[0].pageY - cha + 'px';
//            }
//        })
//    })
    /*$("body")[0].addEventListener('touchstart', function(event) {
        $(".ipt").blur();
    })
    $("#form").submit(function() {
        if (!$(".submit").hasClass("active"))
        {
            if ($(".ipt[name=apply_name]").val() == "")
            {
                $(".ipt[name=apply_name]").focus();
                return false
            }
            if ($(".ipt[name=apply_mobile]").val() == "")
            {
                $(".ipt[name=apply_mobile]").focus();
                return false
            }
            if ($(".ipt[name=apply_company]").val() == "")
            {
                $(".ipt[name=apply_company]").focus();
                return false
            }
            if ($(".ipt[name=apply_title]").val() == "")
            {
                $(".ipt[name=apply_title]").focus();
                return false
            }
            if ($(".ipt[name=city_name]").val() == "")
            {
                $(".ipt[name=city_name]").focus();
                return false
            }
            if (checktel() == false)
            {
                checktel();
                return false;
            }
            $(".ipt").blur();
            $.post('/apply/post', $("#form").serialize(),
                    function(data) {
                        if (!data.error) {
                            $(".ten").appendTo($(".swiper-wrapper"));
                            mySwiper.reInit();
                            $(".ten").css("visibility", "visible");
                            mySwiper.swipeTo(9);
                            return false;
                        } else {
                            alert(data.message);
                            return false;
                        }
                    }
            );
            setTimeout(function() {
                $(".ten").appendTo($(".swiper-wrapper"));
                mySwiper.reInit();
                $(".ten").css("visibility", "visible");
                mySwiper.swipeTo(9, 0);
                $(".swiper-slide").removeClass("on");
                $(".swiper-slide-active").addClass("on");
                if (!$(".swiper-slide-active").next().length)
                {
                    $(".up").hide();
                }
                else
                {
                    $(".up").show();
                }
            }, 400)
        }
        return false;
    })
    function checktel()
    {
        var temp = $(".ipt[type=tel]")[0];
        var myreg = /(((13[0-9]{1})|(18[0-9]{1})|(15[0-9]{1}))+\d{8})$|(^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/;
        if (temp.value != "")
        {
            if (!myreg.test(temp.value))
            {
                alert('鎻愮ず\n\n璇疯緭鍏ユ湁鏁堢殑鎵嬫満鍙风爜锛�');
                $(".ipt[type=tel]")[0].value = "";
                $(".ipt[type=tel]")[0].focus();
                return false;
            }
        }
    }*/
})