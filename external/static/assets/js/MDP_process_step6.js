emotionArray = []

Emotion = function(str,table,class_name){
    this.str =  str;
    this.table = table;
    this.class_name = class_name;

    this.add(this.table,this.class_name);
}
Emotion.prototype = Object.create(Emotion.prototype);

Emotion.prototype.add = function(table,class_name){
    
    let num = 0;
    let tr_num = 0;
    

    this.str.forEach(function(value,index){
        
        if((index) % 5 == 0){
           
            $(table).append('<tr>');
            if(index != 0)
                num++;
            tr_num = 0
            
        }
        
        let checkbox = $('<input/>', {
                id: class_name+index,
                value : value,
                type : "checkbox",
                name : class_name
            });
        let label = $('<div/>', {
                value : value,
                for:class_name+index
            });
  
        label.addClass(class_name);
        label.html(value);
        
        $(table).children().eq(num).append("<td>").children().eq(tr_num).append(checkbox).append(label);
        tr_num++;
        
    });

}

$(document).ready(function(){

    
    let common=["진심의","의지되는","감사한","걱정스러운","기억에 남는",
                "안심이 되는","그리운","배려하는","위로의","재미있는",
                "건강한","밝은","기대되는","행복한","든든한",
                "따스한","보고싶은","편안한","신나는","친절한",
                "빛나는","아름다운"];
    let thanks=["찬란한","정감 있는","나를 위한","감동적인","베푸는",
                "인자한","설레는","벅차는","보답하는","사려깊은"];
    let sorry=["늦었지만","이제라도","사과하는","슬픈","마음 아픈",
                "죄송한","눈물하는","속상한","안타까운","안쓰러운"];
    let celebration=["긍정적인","뿌듯한","믿음","따뜻한","열정적인",
                    "활발한","생기 넘치는","단단한","매력적인","부지런한"];
    let love=["응원하는","여유로운","하나뿐인","사랑스러운","오랫동안",
                "풍부한","간직하고 싶은","고운","부드러운","감싸주는"];
    let say=["화사한","아련한","씁쓸한","단단한","외로운",
                "궁금한","다시 예전처럼","포근한","잔잔한","끈끈한"];
    
    let emotion=[[common,"#emotion_card_table_common","emotion_card_common"],[thanks,"#emotion_card_table_thanks","emotion_card_thanks"],
                [sorry,"#emotion_card_table_sorry","emotion_card_sorry"],[celebration,"#emotion_card_table_celebration","emotion_card_celebration"],
                [love,"#emotion_card_table_love","emotion_card_love"],[say,"#emotion_card_table_say","emotion_card_say"]];

            
    console.log("A");
    emotion.forEach(function(value,index){
        emo = new Emotion(value[0],value[1],value[2]);
        emotionArray.push(emo);
    
    })
    
    let emotionSwiper = new Swiper(".emotionSwiper", {
    
        autoHeight : true,
        allowTouchMove : false,
        on: {
            activeIndexChange: function () {
                let num = this.realIndex+1;
                $("input[id=emotion"+num+"]").prop('checked', true);
                console.log(num);
            }
        },
        keyboard: {
            enabled: false,
            onlyInViewport: false,
            pageUpDown:false
          },
          
    });
    console.log(emotionArray[0].class_name);
   
    $("input:checkbox[name="+emotionArray[0].class_name+"]").click(function(){
        console.log(emotionArray[0].class_name);


    });


    $("input:radio[name=step6_emotion_category]").click(function()
    {
        let emotion_radio = $('input:radio[name="step6_emotion_category"]:checked').val();
        
        switch(emotion_radio){
            case "1": {
                emotionSwiper.slideTo(0);
                break;
            }
            case "2": {
                emotionSwiper.slideTo(1);
                break;
            }
            case "3": {
                emotionSwiper.slideTo(2);
                break;
            }
            case "4": {
                emotionSwiper.slideTo(3);
                break;
            }
            case "5": {
                emotionSwiper.slideTo(4);
                break;
            }
            case "6": {
                emotionSwiper.slideTo(5);
                break;
            }
        }
    });


});







/*


checkbox.html(value);

function add(str,table,class_name){

    
    num = 0;
    tr_num = 0;

    str.forEach(function(value,index){
        if((index) % 5 == 0){
           
            $(table).append('<tr>');
            if(index != 0)
                num++;
            console.log(num);
            tr_num = 0
            
        }
        
        let button = $('<button/>', {
                id: class_name+index,
                value : value
            });
        button.addClass("emotion_card_text");
        button.addClass(class_name);
        button.html(value);
        $(table).children().eq(num).append("<td>").children().eq(tr_num).append(button);
        tr_num++;
        
    });


}


*/