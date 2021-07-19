
$(document).ready(function(){
    

    $("input:radio[name=menu]").click(function()
    {
        console.log("click!")
    });
    
    let menu = $('input:radio[name="rb"]:checked').val();
    console.log(menu);
    menu = 1;
    switch(menu){
        case 1: {
            console.log("1")
            process1();
            // break;
        }
    }

    
    
});

function process1(){

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
        if(  $("#step1_textbox1").val().length > 1 ){
            
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

        if( $("#step1_textbox2").val().length>1 &&  
            $("#step1_textbox3").val().length >1 &&  
            $("#step1_textbox4").val().length>1){
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
 
        if( $("#step1_textbox5").val().length>1 &&  $("#step1_textbox6").val().length >1 &&  
            $("#step1_textbox7").val().length>1 && $("#step1_textbox8").val().length>1 &&  
            $("#step1_textbox9").val().length >1 ){


            $('#step1_shape_num3').removeClass( "step3_shape" );  
        
            $('#step1_textbox5').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox6').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox7').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox8').addClass( "textbox5_9_class" ) ;
            $('#step1_textbox9').addClass( "textbox5_9_class" ) ;
            
            $('#next').fadeIn(600);

            $("#next").click(function(){
                step4();
            });

        }
    }

    function step4(){

        if($("#step1_textbox1").val().length>1 &&  $("#step1_textbox2").val().length >1 &&  
            $("#step1_textbox3").val().length>1 && $("#step1_textbox4").val().length>1 &&  
            $("#step1_textbox5").val().length >1 && $("#step1_textbox6").val().length>1 &&  
            $("#step1_textbox7").val().length >1 &&  $("#step1_textbox8").val().length>1 &&
            $("#step1_textbox9").val().length>1 ){
            
                console.log("4")
                $('#stage1').fadeOut(600);
                $('#stage2').fadeOut(600);
                $('#stage3').fadeOut(600);
                $('#next').fadeOut(600)

                $('#stage4').delay(600).fadeIn(600);

                $("#step1_textbox1").prop("type","radio");


           
                
            
            
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
