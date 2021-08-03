$(document).ready(function(){
    for(let i=2; i<5; i++){
        $(".step5_"+i).hide();
    }
    
    $("#step5_answer1").keyup(function(){
        if($("#step5_answer1").val().length>1){
            $(".step5_2").fadeIn(600);
        }
    })

    $("#step5_answer2").keyup(function(){
        if($("#step5_answer2").val().length>1){
            $(".step5_3").fadeIn(600);
        }
    })

    $("#step5_answer3").keyup(function(){
        if($("#step5_answer3").val().length>1){
            $(".step5_4").fadeIn(600);
        }
    })



})