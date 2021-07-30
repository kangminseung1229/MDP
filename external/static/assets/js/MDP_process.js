
$(document).ready(function(){
    
    let swiper = new Swiper(".entireSwiper", {
    
        autoHeight : true,
        allowTouchMove : false,
        // allowSlideNext:false,
        // allowSlidePrev:false,

        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
        },
        
        on: {
            activeIndexChange: function () {
                // alert(this.realIndex+'번째 slide입니다.');
                // $(input:radio[id=this.realIndex+1]).prop("checked",true);
                console.log();
                let num = this.realIndex+1;
                $("input:radio[id="+num+"]").prop('checked', true)
            }
        },
        keyboard: {
            enabled: false,
            onlyInViewport: false,
            pageUpDown:false
          },
      });

    //   임시로 6페이지로 고정
    swiper.slideTo(5);
  
    $("input:radio[name=menu]").click(function()
    {
        console.log("AAAAAA")
        let menu = $('input:radio[name="menu"]:checked').val();
        
        switch(menu){
            case "1": {
                swiper.slideTo(0);
                break;
            }
            case "2": {
                swiper.slideTo(1);
                break;
            }
            case "3": {
                swiper.slideTo(2);
                break;
            }
            case "4": {
                swiper.slideTo(3);
                break;
            }
            case "5": {
                swiper.slideTo(4);
                break;
            }
            case "6": {
                swiper.slideTo(5);
                break;
            }
        }
    });
    
    
    process1();

    
    // swiper.allowSlideNext(false);

});

function process1(){

    $("#step1_next").hide();
    for(let i =2; i<10; i++){
        $("#step1_textbox"+i).hide();
    }


    $("#step1_textbox1").keyup(function(){
        step1();
    });

    $("#step1_textbox2").keyup(function(){
        step2();
    });

    $("#step1_textbox3").keyup(function(){
        step2();
    });

    $("#step1_textbox4").keyup(function(){
        step2();
    });

    $("#step1_textbox5").keyup(function(){
        step3();
    });

    $("#step1_textbox6").keyup(function(){
        step3();
    });

    $("#step1_textbox7").keyup(function(){
        step3();
    });

    $("#step1_textbox8").keyup(function(){
        step3();
    });

    $("#step1_textbox9").keyup(function(){
        step3();
    });

    
    function step1(){
        if(  $("#step1_textbox1").val().length > 0 ){
            
            console.log("A")
            $('#stage2').fadeIn(600);
            $('#step1_shape_num2').fadeIn(600);

            // TextBox
            $('#step1_textbox2').fadeIn(600);
            $('#step1_textbox3').fadeIn(600);
            $('#step1_textbox4').fadeIn(600);

            // effect 해제
            $('#step1_shape_num1').removeClass( "step1_shape" );
            $('#step1_shape_num2').addClass( "step2_shape" ) ;

            $('#step1_textbox1').addClass( "textbox1_class" ) ;
            
            
        }
    }


    function step2() { 

        if( $("#step1_textbox2").val().length>0 &&  
            $("#step1_textbox3").val().length >0 &&  
            $("#step1_textbox4").val().length>0){
                console.log("abc")
                $('#stage3').fadeIn(600);
                $('#step1_shape_num3').fadeIn(600);

                // TextBox
                $('#step1_textbox5').fadeIn(600);
                $('#step1_textbox6').fadeIn(600);
                $('#step1_textbox7').fadeIn(600);
                $('#step1_textbox8').fadeIn(600);
                $('#step1_textbox9').fadeIn(600);

                // effect 해제
                $('#step1_shape_num2').removeClass( "step2_shape" );
                $('#step1_shape_num3').addClass( "step3_shape" ) ;

                $('#step1_textbox2').addClass( "textbox2_4_class" ) ;
                $('#step1_textbox3').addClass( "textbox2_4_class" ) ;
                $('#step1_textbox4').addClass( "textbox2_4_class" ) ;

        }
    }

    function step3() { 
 
        if( $("#step1_textbox5").val().length>0 &&  $("#step1_textbox6").val().length >0 &&  
            $("#step1_textbox7").val().length>0 && $("#step1_textbox8").val().length>0 &&  
            $("#step1_textbox9").val().length >0 ){


            $('#step1_shape_num3').removeClass( "step3_shape" );  
        
            $('#step1_textbox5').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox6').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox7').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox8').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox9').addClass( "textbox5_9_class" ) ;
            
            $('#step1_next').fadeIn(600);

            

        }
    }

    $("#step1_next").click(function(){
        step4();
    });

    function step4(){

        if($("#step1_textbox1").val().length>0 &&  $("#step1_textbox2").val().length >0 &&  
            $("#step1_textbox3").val().length>0 && $("#step1_textbox4").val().length>0 &&  
            $("#step1_textbox5").val().length >0 && $("#step1_textbox6").val().length>0 &&  
            $("#step1_textbox7").val().length >0 &&  $("#step1_textbox8").val().length>0 &&
            $("#step1_textbox9").val().length>0 ){
            
                console.log("4")
                $('#stage1').fadeOut(600);
                $('#stage2').fadeOut(600);
                $('#stage3').fadeOut(600);
                $('#next').fadeOut(600)

                $('#stage4').delay(600).fadeIn(600);
                

                $("#step1_textbox1").prop("readonly",true);
                $("#step1_textbox1").prop("width","auto");

                for(let i = 2; i<10; i++){
                    let textbox = "step1_textbox"+i;
                    $("#"+textbox).prop("type","radio");
                    $("#"+textbox).after("<label class ="+textbox+" id = step1_label"+i+">"+$("#"+textbox).val()+"</label>");
                }
                

                
                
            
            
            }

        
    }

  
}



//onkeyup
// $(document).ready(function(){
//     $('')
// })


// id = html에서 1개
// class = 여러개 적용가능

// name = 여러개 체크박스, 라디오

// $("#id").val(); //id가져오기
// $('.class').val("1");

// $("#id").attr();
// $("#id").prop();//속성을 오리지널 접근

// local git     
