
$(document).ready(function(){
    
    swiper = new Swiper(".entireSwiper", {
    
        // autoHeight : true,
        // calculateHeight:true,
        allowTouchMove : false,
        // allowSlideNext:true,
        // allowSlidePrev:true,

        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
        },
        
        on: {
            activeIndexChange: function () {
                num = this.realIndex+1;
                $("input:radio[id="+num+"]").prop('checked', true);
                $('html, body').scrollTop(0);
            }
        },
        keyboard: {
            enabled: false,
            onlyInViewport: false,
            pageUpDown:false
          }
    });
    $('.swiper-button-next').addClass( "disabled" );
    $('.swiper-button-prev').addClass( "disabled" );
    //   임시로 페이지로 고정
    $('html, body').scrollTop(0);
    // swiper.slideTo(2);

    //순서대로 하는 거 
    // for(let i =2; i<=6;i++){
    //     $("#"+i).attr("disabled", "disabled");
    // }
    $("input:radio[name=menu]").change(function()
    {
        let menu = $('input:radio[name="menu"]:checked').val();
        
        switch(menu){
            case "1": {
                $('html, body').scrollTop(0);
                swiper.slideTo(0);
                break;
            }
            case "2": {
                $('html, body').scrollTop(0);
                swiper.slideTo(1);
                break;
            }
            case "3": {
                $('html, body').scrollTop(0);
                swiper.slideTo(2);
                // $("#step3_textbox1").focus();
                break;
            }
            case "4": {
                $('html, body').scrollTop(0);
                swiper.slideTo(3);
                break;
            }
            case "5": {
                $('html, body').scrollTop(0);
                swiper.slideTo(4);
                // $("#step5_answer1").focus();
                break;
            }
            case "6": {
                $('html, body').scrollTop(0);
                swiper.slideTo(5);
                break;
            }
        }
    });
    
    
    step1();
    step2();
    step3();
    step4();
    step5();

    
    

});



//****step1 Java Script****
function step1(){

    let nowStage;
    // 모든 Textbox hide
    $("#step1_next").hide();
    for(let i =2; i<10; i++){
        $("#step1_textbox"+i).hide();
    }
    
    // 사용자가 텍스트 박스 입력시 단계별로 fade out
    onkeyUpFunc(1,stage1);
    onkeyUpFunc(2,stage2);
    onkeyUpFunc(3,stage2);
    onkeyUpFunc(4,stage2);
    onkeyUpFunc(5,stage3);
    onkeyUpFunc(6,stage3);
    onkeyUpFunc(7,stage3);
    onkeyUpFunc(8,stage3);
    onkeyUpFunc(9,stage3);
    step1Next();
    
    function onkeyUpFunc(num,func) {
        $("#step1_textbox"+num).keyup(function(){
            func();
        });
    }

    // step1 : stage1
    function stage1(){
        if(  $("#step1_textbox1").val().length > 0 ){
            nowStage = 1;
            $('#stage2').fadeIn(600);
            $('#step1_shape_num2').fadeIn(600);

            // TextBox
            for(let i =2; i<=4; i++){
                $('#step1_textbox'+i).fadeIn(600);
            }
            // effect 해제
            $('#step1_shape_num1').removeClass( "step1_shape" );
            $('#step1_shape_num2').addClass( "step2_shape" );

            $('#step1_textbox1').addClass( "textbox1_class" );
            
            
        }
    }
    // step1 : stage2
    function stage2() { 
        nowStage = 2;
        if( $("#step1_textbox2").val().length>0 &&  
            $("#step1_textbox3").val().length >0 &&  
            $("#step1_textbox4").val().length>0){
                $('#stage3').fadeIn(600);
                $('#step1_shape_num3').fadeIn(600);

                // TextBox
                for(let i =5; i<=9; i++){
                    $('#step1_textbox'+i).fadeIn(600);
                }

                // effect 해제
                $('#step1_shape_num2').removeClass( "step2_shape" );
                $('#step1_shape_num3').addClass( "step3_shape" ) ;

                for(let i =2; i<=4; i++){
                    $('#step1_textbox'+i).addClass( "textbox2_4_class" );
                }

        }
    }
    // step1 : stage3
    function stage3() { 
        nowStage = 3;
        if( $("#step1_textbox5").val().length>0 &&  $("#step1_textbox6").val().length >0 &&  
            $("#step1_textbox7").val().length>0 && $("#step1_textbox8").val().length>0 &&  
            $("#step1_textbox9").val().length >0 ){

            $('#step1_shape_num3').removeClass( "step3_shape" );  
        
            for(let i =5; i<=9; i++){
                $('#step1_textbox'+i).addClass( "textbox5_9_class" );
            }
            
            $('#step1_next').fadeIn(600);
            
        }
    }

    // next 버튼 함수
    function step1Next(){
        $("#step1_next").click(function(){

            if( $("#step1_textbox5").val().length>0 &&  $("#step1_textbox6").val().length >0 &&  
            $("#step1_textbox7").val().length>0 && $("#step1_textbox8").val().length>0 &&  
            $("#step1_textbox9").val().length >0 ){
                if(nowStage == 3){
                    stage4();
                }
                else if(nowStage == 4) {
                    swiper.slideTo(1);
                    $('#step1_next').fadeOut(600);
                    $("#2").removeAttr("disabled");        
                    
                }
            }
        });
    }
    
    // step1 : stage4
    function stage4(){
        nowStage = 4;
        if($("#step1_textbox1").val().length>0 &&  $("#step1_textbox2").val().length >0 &&  
            $("#step1_textbox3").val().length>0 && $("#step1_textbox4").val().length>0 &&  
            $("#step1_textbox5").val().length >0 && $("#step1_textbox6").val().length>0 &&  
            $("#step1_textbox7").val().length >0 &&  $("#step1_textbox8").val().length>0 &&
            $("#step1_textbox9").val().length>0 ){
            
                $('#stage1').fadeOut(600);
                $('#stage2').fadeOut(600);
                $('#stage3').fadeOut(600);
                $('#step1_next').fadeOut(600)

                $('#stage4').delay(600).fadeIn(600);

                $("#step1_textbox1").prop("readonly",true);
                $("#step1_textbox1").prop("width","auto");

                for(let i = 2; i<10; i++){
                    let textbox = "step1_textbox"+i;
                    $("#"+textbox).prop("type","radio");
                    $("#"+textbox).after("<label class ="+textbox+" id = step1_label"+i+">"+$("#"+textbox).val()+"</label>");
                }
                
                $("input[name = step1Text]").click(function(){
                    step1Next("step2");
                    $('#step1_next').fadeIn(600);
                });
               
                
        }
    }
}


//****step2 Java Script****
function step2(){

    $("#step2_next").hide();
    $(".step2_emotion_wrapper").hide();

    // 파일 업로드가 되었을 경우
    $("#fileupload").change(function(){
        const imgPath=document.querySelector('#fileupload').files[0];
        const reader = new FileReader();
        reader.addEventListener("load", function(){
            localStorage["target"]=reader.result;
        }, false);
        if(imgPath){
            reader.readAsDataURL(imgPath);
        }
        let img=document.getElementById('target');
        // img.src=localStorage["target"];
        setTimeout(function(){
            $("#target").prop("src",localStorage["target"]);
        }, 50)
        
        if($("#target").attr("src")!=null){
            $(".input_image").css("background-color", "white");
            $(".input_image").css("border", "none");
            $("#fileupload").css("display", "none");
        }

        $(".step2_emotion_wrapper").fadeIn(600);

    });


    $(".feel").click(function(){
        $("#step2_next").fadeIn(600);
    });

    
    step2Next();
    

    // next 버튼 함수
    function step2Next(){
        $("#step2_next").click(function(){
            // $("#step3_textbox1").focus();
            swiper.slideTo(2);
            $('#step2_next').fadeOut(600);
            $("#3").removeAttr("disabled");        
        });
    }

    function send(param) {  

        if ($("#"+param).val() == undefined ) {
            alert(param +'이 없습니다.');
        }

    }

}

//****step3 Java Script****
function step3(){

    $('#step3_next').hide();
    // for(let i=2; i<10; i++){
    //     $("#step3_"+i).hide();
    // }
    onkeyUpFunc(1);
    onkeyUpFunc(2);
    onkeyUpFunc(3);
    onkeyUpFunc(4);
    onkeyUpFunc(5);
    onkeyUpFunc(6);
    onkeyUpFunc(7);
    onkeyUpFunc(8);
    onkeyUpFunc(9);

    function onkeyUpFunc(num) {
        $("#step3_textbox"+num).keyup(function(){
         
            if($("#step3_textbox1").val().length>0 &&  $("#step3_textbox2").val().length >0 &&  
            $("#step3_textbox3").val().length>0 && $("#step3_textbox4").val().length>0 &&  
            $("#step3_textbox5").val().length >0 && $("#step3_textbox6").val().length>0 &&  
            $("#step3_textbox7").val().length >0 &&  $("#step3_textbox8").val().length>0 &&
            $("#step3_textbox9").val().length>0)
                $('#step3_next').fadeIn(600);
           
        });
    }

    $("#step3_next").click(function(){

        if($("#step3_textbox1").val().length>0 &&  $("#step3_textbox2").val().length >0 &&  
                $("#step3_textbox3").val().length>0 && $("#step3_textbox4").val().length>0 &&  
                $("#step3_textbox5").val().length >0 && $("#step3_textbox6").val().length>0 &&  
                $("#step3_textbox7").val().length >0 &&  $("#step3_textbox8").val().length>0 &&  
                $("#step3_textbox9").val().length>0)
        {
            swiper.slideTo(3);
            $('#step3_next').fadeOut(600);
            // $("#4").removeAttr("disabled");        
        }
       
    });
}

//****step4 Java Script****
function step4(){
    

    $('#step4_next').hide();
    
    for(let i = 1; i <=4; i++){
        $('textarea[name=step4Text'+i+']').keyup(function() {
            // 텍스트영역의 길이를 체크
            var textLength1 = $("textarea[name=step4Text1]").val().length;
            var textLength2 = $("textarea[name=step4Text2]").val().length;
            var textLength3 = $("textarea[name=step4Text3]").val().length;
            var textLength4 = $("textarea[name=step4Text4]").val().length;
            
            if(textLength1 > 0 && textLength2 > 0 && textLength3 > 0 && textLength4 > 0)
                $('#step4_next').fadeIn(600);
             
        });
    }

    $("#step4_next").click(function(){

        var textLength1 = $("textarea[name=step4Text1]").val().length;
        var textLength2 = $("textarea[name=step4Text2]").val().length;
        var textLength3 = $("textarea[name=step4Text3]").val().length;
        var textLength4 = $("textarea[name=step4Text4]").val().length;
            
        if(textLength1 > 0 && textLength2 > 0 && textLength3 > 0 && textLength4 > 0)
        {
            // $("#step5_answer1").focus();
            swiper.slideTo(4);
            $('#step4_next').fadeOut(600);
            $("#5").removeAttr("disabled");       
        }

             
        
    });
    
}

//****step5 Java Script****
function step5(){
    
    
    $('#step5_next').hide();
   
    // for(let i=2; i<5; i++){
    //     $("#step5_question"+i).hide();
    //     $("#step5_answer"+i).hide();
    // };
    
    // $("#step5_answer1").keyup(function(){
    //     if($("#step5_answer1").val().length>1){
    //         $('#step5_answer1').removeClass( "step5_answer1_focus" );
    //         $("#step5_question2").fadeIn(600);
    //         $("#step5_answer2").delay(300).fadeIn(600);
    //     }
    // });

    // step5Keyup(2);
    // step5Keyup(3);

    // $("#step5_answer4").keyup(function(){
    //     if($("#step5_answer4").val().length>1) 
    //         $('#step5_next').fadeIn(600);
    // });
    checkStep5(1);
    checkStep5(2);
    checkStep5(3);
    checkStep5(4);

    function checkStep5(num){
        $("#step5_answer"+num).keyup(function(){
            if($("#step5_answer1").val().length>1 && $("#step5_answer2").val().length>1 &&
            $("#step5_answer3").val().length>1 && $("#step5_answer4").val().length>1 ) {
                $('#step5_next').fadeIn(600);
            }
        });
    }
   
    $('#step5_next').click(function(){
        if($("#step5_answer1").val().length>1 && $("#step5_answer2").val().length>1 &&
            $("#step5_answer3").val().length>1 && $("#step5_answer4").val().length>1 ) 
        {
            swiper.slideTo(5);
            $('#step5_next').fadeOut(600);
            $("#6").removeAttr("disabled");    
            // $('.swiper-button-next').removeClass( "disabled" );
            // $('.swiper-button-prev').removeClass( "disabled" );        
        }

                
    });

    function step5Keyup(num){
        $("#step5_answer"+num).keyup(function(){
            if($("#step5_answer"+num).val().length>1){
                num++;
                $("#step5_question"+num).fadeIn(600);
                $("#step5_answer"+num).delay(300).fadeIn(600);
                
            }
        });
    }
    
}

function checkProcess(){

    let checkString="";
    let count = 0;
    $('input:checkbox[name=step6_emotion_check]').each(function() {

        if(this.checked){

            if(count==0)
                checkString = this.value;
            else{
                checkString = checkString + "|" + this.value;
            }
            count++;
        }
        $("#step6_emotion_result").val(checkString);
    });

    // if( $("#step1_textbox1").val().length == 0 || $("#step1_textbox2").val().length == 0 || 
    // $("#step1_textbox3").val().length == 0 ||  $("#step1_textbox4").val().length == 0 || 
    // $("#step1_textbox5").val().length == 0 ||  $("#step1_textbox6").val().length == 0 || 
    // $("#step1_textbox7").val().length == 0 || $("#step1_textbox8").val().length == 0 || 
    // $("#step1_textbox9").val().length == 0 || 
    //  $('input:radio[name=step1Text]').checked() == false ){
    //     swiper.slideTo(0);
    //     alert('01.받는 사람 : 아직 답하지 못한 질문이 있어요');
    //     return false;
    // }

    // if(  $("#feeling").val() == "" ){

    //     swiper.slideTo(0);
    //     alert('02.되돌아 보며 : 아직 답하지 못한 질문이 있어요');
    //     return false;
    // }



    if($("#step3_textbox1").val().length == 0 ||  $("#step3_textbox2").val().length == 0 ||  
    $("#step3_textbox3").val().length==0 || $("#step3_textbox4").val().length==0 ||  
    $("#step3_textbox5").val().length ==0 || $("#step3_textbox6").val().length==0 ||  
    $("#step3_textbox7").val().length ==0 ||  $("#step3_textbox8").val().length==0 ||  
    $("#step3_textbox9").val().length==0 ){
        // $("#step3_textbox1").focus();
        swiper.slideTo(2);
        alert('03.그 사람과 나는 : 아직 답하지 못한 질문이 있어요');
        return false;
    }

    else if($("textarea[name=step4Text1]").val().length == 0 || $("textarea[name=step4Text2]").val().length  == 0 ||
    $("textarea[name=step4Text3]").val().length  == 0 || $("textarea[name=step4Text4]").val().length  == 0 ){
        swiper.slideTo(3);
        alert('04.그때의 나는 : 아직 답하지 못한 질문이 있어요');
        return false;
    }

    else if($("#step5_answer1").val().length==0 || $("#step5_answer2").val().length==0 ||
    $("#step5_answer3").val().length==0 || $("#step5_answer4").val().length==0){
        swiper.slideTo(4);
        // $("#step5_answer1").focus();
        alert('05.그때의 내 마음은 : 아직 답하지 못한 질문이 있어요');
        return false;
    }

    else if($("input:checkbox[name=step6_emotion_check:checked").length == 0){
        swiper.slideTo(5);
        alert('06.감정단어 : 하나 이상 단어카드를 선택해주세요');
        return false;
    }

    
  

}
