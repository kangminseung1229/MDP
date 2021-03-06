$(document).ready(function(){
    // Swiper 객체 생성
    swiper = new Swiper(".entireSwiper", {
        // Swiper 속성 설정

        // swipe 터치 막음
        allowTouchMove: false,
        
        // Swiper index에 따라 메뉴 라디오 버튼 활성화
        on: {
            activeIndexChange: function () {
                num = this.realIndex+1;
                $("input:radio[id="+num+"]").prop('checked', true);
                $('html, body').scrollTop(0);
            }
        },

        // 네비게이션 버튼
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
        },
        // 키보드 이벤트 막기
        keyboard: {
            enabled: false,
            onlyInViewport: false,
            pageUpDown: false
        }
    });

    // Swiper 내비게이션 버튼 비활성화
    $('.swiper-button-next').addClass( "disabled" );
    $('.swiper-button-prev').addClass( "disabled" );
    
    // 첫번째 Swiper 제일 상단
    $('html, body').scrollTop(0);
    swiper.slideTo(0);

    // 라디오 메뉴 버튼 클릭시 Swiper 연동
    $("input:radio[name=menu]").change(function()
    {
        let menu = $('input:radio[name="menu"]:checked').val();
        
        switch(menu){
            case "1": { //Step1 받는 사람
                $('html, body').scrollTop(0);
                swiper.slideTo(0);
                break;
            }
            case "2": { //Step2 되돌아 보며
                $('html, body').scrollTop(0);
                swiper.slideTo(1);
                break;
            }
            case "3": { //Step3 그 사람과 나는
                $('html, body').scrollTop(0);
                swiper.slideTo(2);
                break;
            }
            case "4": { //Step4 그때의 나는
                $('html, body').scrollTop(0);
                swiper.slideTo(3);
                break;
            }
            case "5": { //Step5 그때의 내 마음은
                $('html, body').scrollTop(0);
                swiper.slideTo(4);
                break;
            }
            case "6": { //Step6 감정단어
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
function step1() {

    let nowStage;
    
    // 사용자가 텍스트 박스 입력시 단계별로 fade out
    onkeyUpFunc(1, stage1);
    onkeyUpFunc(2, stage2);
    onkeyUpFunc(3, stage2);
    onkeyUpFunc(4, stage2);
    onkeyUpFunc(5, stage3);
    onkeyUpFunc(6, stage3);
    onkeyUpFunc(7, stage3);
    onkeyUpFunc(8, stage3);
    onkeyUpFunc(9, stage3);
    step1Next();
    
    // 텍스트박스의 key를 up했을때 해당 함수 실행
    function onkeyUpFunc(num,func) {
        $("#step1_textbox"+num).keyup(function(){
            func();
        });
    }

    // step1 : stage1
    function stage1() {
        // 텍스트를 작성하였을 경우
        if ($("#step1_textbox1").val().length > 0) {
            nowStage = 1;
            $('#stage2').fadeIn(600);
            $('#step1_shape_num2').fadeIn(600);

            // TextBox 보이기
            for (let i = 2; i <= 4; i++) {
                $('#step1_textbox' + i).fadeIn(600);
            }
            // effect 해제
            $('#step1_shape_num1').removeClass("step1_shape");
            $('#step1_shape_num2').addClass("step2_shape");

            $('#step1_textbox1').addClass("textbox1_class");

        }
    }
    // step1 : stage2
    function stage2() {
        nowStage = 2;
        // 텍스트를 작성하였을 경우
        if( $("#step1_textbox2").val().length>0 &&  
            $("#step1_textbox3").val().length >0 &&  
            $("#step1_textbox4").val().length>0){
                $('#stage3').fadeIn(600);
                $('#step1_shape_num3').fadeIn(600);

                // TextBox 나타나기
                for(let i=5; i<=9; i++){
                    $('#step1_textbox'+i).fadeIn(600);
                }

            // effect 해제
            $('#step1_shape_num2').removeClass("step2_shape");
            $('#step1_shape_num3').addClass("step3_shape");

            for (let i = 2; i <= 4; i++) {
                $('#step1_textbox' + i).addClass("textbox2_4_class");
            }
        }
    }
    // step1 : stage3
    function stage3() {
        nowStage = 3;
        // 텍스트를 작성하였을 경우
        if( $("#step1_textbox5").val().length>0 &&  $("#step1_textbox6").val().length >0 &&  
            $("#step1_textbox7").val().length>0 && $("#step1_textbox8").val().length>0 &&  
            $("#step1_textbox9").val().length >0 ){
            
            // effect 해제
            $('#step1_shape_num3').removeClass( "step3_shape" );  
            
            for(let i =5; i<=9; i++){
                $('#step1_textbox'+i).addClass( "textbox5_9_class" );
            }
            // 다음단계 넘어가기
            $('#step1_next').fadeIn(600);

        }
    }

    // next 버튼 함수
    function step1Next() {
        // next버튼을 눌렀을 경우
        $("#step1_next").click(function () {
            // 모든 텍스트가 작성되었을 경우
            if ($("#step1_textbox5").val().length > 0 && $("#step1_textbox6").val().length > 0 &&
                $("#step1_textbox7").val().length > 0 && $("#step1_textbox8").val().length > 0 &&
                $("#step1_textbox9").val().length > 0) {
                
                // stage3에서 버튼을 눌렀을 때
                if (nowStage == 3) {
                    stage4();   //한 사람을 선택하는 함수 실행
                }
                // stage4에서 버튼을 눌렀을 때
                else if (nowStage == 4) {
                    swiper.slideTo(1); // Step2로 넘어가기
                    $('html, body').scrollTop(0);   // 스크롤 맨 위
                    $("#2").removeAttr("disabled");        
                }
            }
        });
    }
    
    // step1 : stage4 8개의 사람 중 편지를 전하고자 하는 사람 선택
    function stage4(){
        nowStage = 4;
        // 모든 텍스트가 작성되었는지 확인
        if($("#step1_textbox1").val().length>0 &&  $("#step1_textbox2").val().length >0 &&  
            $("#step1_textbox3").val().length>0 && $("#step1_textbox4").val().length>0 &&  
            $("#step1_textbox5").val().length >0 && $("#step1_textbox6").val().length>0 &&  
            $("#step1_textbox7").val().length >0 &&  $("#step1_textbox8").val().length>0 &&
            $("#step1_textbox9").val().length>0 ){
                
                // 설명 안내문 사라지기
                $('#stage1').fadeOut(600);
                $('#stage2').fadeOut(600);
                $('#stage3').fadeOut(600);
                // next 버튼 사라지기
                $('#step1_next').fadeOut(600);
                
                // 다음 안내문 보이기
                $('#stage4').delay(600).fadeIn(600);

                // 텍스트 박스 읽기 전용으로 변경
                $("#step1_textbox1").prop("readonly",true);
                $("#step1_textbox1").prop("width","auto");

                // 모든 텍스트박스들을 라디오로 변경
                for(let i = 2; i<10; i++){
                    let textbox = "step1_textbox"+i;
                    $("#"+textbox).prop("type","radio");
                    $("#"+textbox).after("<label class ="+textbox+" id = step1_label"+i+">"+$("#"+textbox).val()+"</label>");
                }
                
                // 한 사람 선택이 되면 다음 단계로 넘어갈 수 있음
                $("input[name = step1Text]").click(function(){
                    step1Next("step2");
                    $('#step1_next').fadeIn(600);
                });
               
                
        }
    }
}


//****step2 Java Script****
function step2() {
    
    // 파일 업로드가 되었을 경우
    $("#fileupload").change(function () {
        const imgPath = document.querySelector('#fileupload').files[0];
        const reader = new FileReader();
        reader.addEventListener("load", function () {
            localStorage["target"] = reader.result;
        }, false);
        if (imgPath) {
            reader.readAsDataURL(imgPath);
        }
        let img = document.getElementById('target');
        setTimeout(function () {
            $("#target").prop("src", localStorage["target"]);
        }, 50)

        if ($("#target").attr("src") != null) {
            $(".input_image").css("background-color", "white");
            $(".input_image").css("border", "none");
            $("#fileupload").css("display", "none");
        }

        $(".step2_emotion_wrapper").fadeIn(600);

    });

    // 전하고 싶은 감정을 클릭하면 다음 버튼 보이기
    $(".feel").click(function () {
        $("#step2_next").fadeIn(600);
    });


    step2Next();


    // next 버튼 함수
    function step2Next() {
        $("#step2_next").click(function () {
            swiper.slideTo(2);  // 다음 슬라이드로 넘어가기
            $('html, body').scrollTop(0);   // 스크롤 맨 위
            $("#3").removeAttr("disabled");        
        });
    }

    function send(param) {

        if ($("#" + param).val() == undefined) {
            alert(param + '이 없습니다.');
        }

    }

}

//****step3 Java Script****
function step3() {

    onkeyUpFunc(1);
    onkeyUpFunc(2);
    onkeyUpFunc(3);
    onkeyUpFunc(4);
    onkeyUpFunc(5);
    onkeyUpFunc(6);
    onkeyUpFunc(7);
    onkeyUpFunc(8);
    onkeyUpFunc(9);

    // 텍스트 작성시 텍스트를 다 작성하면 다음 버튼으로 보여주기
    function onkeyUpFunc(num) {
        $("#step3_textbox" + num).keyup(function () {

            if ($("#step3_textbox1").val().length > 0 && $("#step3_textbox2").val().length > 0 &&
                $("#step3_textbox3").val().length > 0 && $("#step3_textbox4").val().length > 0 &&
                $("#step3_textbox5").val().length > 0 && $("#step3_textbox6").val().length > 0 &&
                $("#step3_textbox7").val().length > 0 && $("#step3_textbox8").val().length > 0 &&
                $("#step3_textbox9").val().length > 0)
                $('#step3_next').fadeIn(600);

        });
    }
    // next 버튼을 누르면 다음 슬라이드로 넘어가기
    $("#step3_next").click(function () {

        if ($("#step3_textbox1").val().length > 0 && $("#step3_textbox2").val().length > 0 &&
            $("#step3_textbox3").val().length > 0 && $("#step3_textbox4").val().length > 0 &&
            $("#step3_textbox5").val().length > 0 && $("#step3_textbox6").val().length > 0 &&
            $("#step3_textbox7").val().length > 0 && $("#step3_textbox8").val().length > 0 &&
            $("#step3_textbox9").val().length > 0) {

            $('html, body').scrollTop(0);   // 스크롤 맨 위
            swiper.slideTo(3);
        }

    });
}

//****step4 Java Script****
function step4() {

    // 텍스트 작성시 텍스트를 다 작성하면 다음 버튼으로 보여주기
    for (let i = 1; i <= 4; i++) {
        $('textarea[name=step4Text' + i + ']').keyup(function () {
            // 텍스트영역의 길이를 체크
            var textLength1 = $("textarea[name=step4Text1]").val().length;
            var textLength2 = $("textarea[name=step4Text2]").val().length;
            var textLength3 = $("textarea[name=step4Text3]").val().length;
            var textLength4 = $("textarea[name=step4Text4]").val().length;

            if (textLength1 > 0 && textLength2 > 0 && textLength3 > 0 && textLength4 > 0)
                $('#step4_next').fadeIn(600);

        });
    }
    // next 버튼을 누르면 다음 슬라이드로 넘어가기
    $("#step4_next").click(function () {

        var textLength1 = $("textarea[name=step4Text1]").val().length;
        var textLength2 = $("textarea[name=step4Text2]").val().length;
        var textLength3 = $("textarea[name=step4Text3]").val().length;
        var textLength4 = $("textarea[name=step4Text4]").val().length;

        if (textLength1 > 0 && textLength2 > 0 && textLength3 > 0 && textLength4 > 0) {
            swiper.slideTo(4);
            $('html, body').scrollTop(0);   // 스크롤 맨 위
            $("#5").removeAttr("disabled");       
        }



    });

}

//****step5 Java Script****
function step5() {

    checkStep5(1);
    checkStep5(2);
    checkStep5(3);
    checkStep5(4);

    // 텍스트 작성시 텍스트를 다 작성하면 다음 버튼으로 보여주기
    function checkStep5(num) {
        $("#step5_answer" + num).keyup(function () {
            if ($("#step5_answer1").val().length > 1 && $("#step5_answer2").val().length > 1 &&
                $("#step5_answer3").val().length > 1 && $("#step5_answer4").val().length > 1) {
                $('#step5_next').fadeIn(600);
            }
        });
    }

    // next 버튼을 누르면 다음 슬라이드로 넘어가기
    $('#step5_next').click(function () {
        if ($("#step5_answer1").val().length > 1 && $("#step5_answer2").val().length > 1 &&
            $("#step5_answer3").val().length > 1 && $("#step5_answer4").val().length > 1) {
            swiper.slideTo(5);
            $('html, body').scrollTop(0);   // 스크롤 맨 위
            $("#6").removeAttr("disabled");    
        }

    });

}

// letter로 넘어가기 전 check 함수
function checkProcess() {

    let checkString = [];
    let count = 0;

    // 사용자가 선택한 감정카드 text value 넘겨주기
    $(".select_word").children().each(function (index, item) {

        if (count == 0)
            checkString = $(item).text();
        else {
            checkString = checkString + "|" + $(item).text();
        }
        count++;
    });
    $("#step6_emotion_result").val(checkString);

    // 각 step별 모든 답변이 완료되었는지 체크

    // Step1
    if ($("#step1_textbox1").val().length == 0 || $("#step1_textbox2").val().length == 0 ||
        $("#step1_textbox3").val().length == 0 || $("#step1_textbox4").val().length == 0 ||
        $("#step1_textbox5").val().length == 0 || $("#step1_textbox6").val().length == 0 ||
        $("#step1_textbox7").val().length == 0 || $("#step1_textbox8").val().length == 0 ||
        $("#step1_textbox9").val().length == 0 || $('input:radio[name=step1Text]').is(":checked") == false) {
        swiper.slideTo(0);
        alert('01.받는 사람 : 아직 답하지 못한 질문이 있어요');
        return false;
    }
     // Step2
    else if ($("#feeling").val() == "") {

        swiper.slideTo(1);
        alert('02.되돌아 보며 : 아직 답하지 못한 질문이 있어요');
        return false;
    }
     // Step3
    else if ($("#step3_textbox1").val().length == 0 || $("#step3_textbox2").val().length == 0 ||
        $("#step3_textbox3").val().length == 0 || $("#step3_textbox4").val().length == 0 ||
        $("#step3_textbox5").val().length == 0 || $("#step3_textbox6").val().length == 0 ||
        $("#step3_textbox7").val().length == 0 || $("#step3_textbox8").val().length == 0 ||
        $("#step3_textbox9").val().length == 0) {
        swiper.slideTo(2);
        alert('03.그 사람과 나는 : 아직 답하지 못한 질문이 있어요');
        return false;
    }
     // Step4
    else if ($("textarea[name=step4Text1]").val().length == 0 || $("textarea[name=step4Text2]").val().length == 0 ||
        $("textarea[name=step4Text3]").val().length == 0 || $("textarea[name=step4Text4]").val().length == 0) {
        swiper.slideTo(3);
        alert('04.그때의 나는 : 아직 답하지 못한 질문이 있어요');
        return false;
    }
    // Step5
    else if ($("#step5_answer1").val().length == 0 || $("#step5_answer2").val().length == 0 ||
        $("#step5_answer3").val().length == 0 || $("#step5_answer4").val().length == 0) {
        swiper.slideTo(4);
        alert('05.그때의 내 마음은 : 아직 답하지 못한 질문이 있어요');
        return false;
    }
}