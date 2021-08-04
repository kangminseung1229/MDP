emotionArray = new Array();
checkedId = new Array();
Emotion = function(str,table,class_name){
    this.str =  str;
    this.table = table;
    this.class_name = class_name;
    this.id_name = [];

    this.add(this.table,this.class_name);
}
Emotion.prototype = Object.create(Emotion.prototype);

Emotion.prototype.add = function(table,class_name){
    
    let num = 0;
    let tr_num = 0;
    let id_name = []

    this.str.forEach(function(value,index){
        
        if((index) % 5 == 0){
           
            $(table).append('<tr>');
            if(index != 0)
                num++;
            tr_num = 0
            
        }

        id_name.push(class_name+index);
        let checkbox = $('<input/>', {
                id: class_name+index,
                value : value,
                type : "checkbox",
                name : "step6_emotion_check"
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

    this.id_name = id_name;

}

Emotion.prototype.classSearch = function(search){

    if(this.str.indexOf(search) < 0){
        return false;
    }
    return this.class_name;
}

$(document).ready(function(){

    
    let common=["진심의","의지되는","감사한","걱정스러운","기억에 남는",
                "친절한","안심이 되는","그리운","배려하는","위로의","재미있는",
                "빛나는","건강한","밝은","기대되는","행복한","든든한","아름다운",
                "따스한","보고싶은","편안한","신나는"];
    let thanks=["찬란한","정감 있는","나를 위한","감동적인","베푸는",
                "인자한","설레는","벅차는","보답하는","사려깊은"];
    let sorry=["늦었지만","이제라도","사과하는","슬픈","마음 아픈",
                "죄송한","눈물나는","속상한","안타까운","안쓰러운"];
    let celebration=["긍정적인","뿌듯한","믿음직한","따뜻한","열정적인",
                    "활발한","생기 넘치는","매력적인","부지런한"];
    let love=["응원하는","여유로운","하나뿐인","사랑스러운","오랫동안",
                "풍부한","간직하고 싶은","고운","부드러운","감싸주는"];
    let say=["화사한","아련한","씁쓸한","단단한","외로운",
                "궁금한","다시 예전처럼","포근한","잔잔한","끈끈한"];
    
    let emotion=[[common,"#emotion_card_table_common","emotion_card_common"],[thanks,"#emotion_card_table_thanks","emotion_card_thanks"],
                [sorry,"#emotion_card_table_sorry","emotion_card_sorry"],[celebration,"#emotion_card_table_celebration","emotion_card_celebration"],
                [love,"#emotion_card_table_love","emotion_card_love"],[say,"#emotion_card_table_say","emotion_card_say"]];

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

    $(".emotionSwiper").hide();
    $(".step6_container_select").hide();
    $("#step6_next").hide();

    let idNum = 0;
    let firstCount = false;
    emotionArray.forEach(function(value){
        value.id_name.forEach(function(nameValue){
            $("#"+nameValue).change(function(){
                
                $(".step6_container_select").fadeIn(600);
                $("#step6_next").delay(300).fadeIn(600);

                let click_value = $("#"+nameValue).val();
                let checked = $("#"+nameValue).is(':checked');
                console.log(checked);
                if(checked){
                    addEmotion(click_value,idNum);
                    idNum++;
                }
                else{  
                    console.log(click_value);
                    deleteEmotion($("#"+nameValue));
                }
                checkedId.forEach(function(value){
                    $("#"+value).click(function(){
                        deleteEmotion($("#"+value));
                    });
                });
               
            })
        })
    });

    // 내가 선택한 감정단어 : 추가
    function addEmotion(emotion,idNum){
        let last_node_count = $("#select_word").children().last().children().length;
        console.log(last_node_count);
        if(last_node_count>=5 || last_node_count == 0){
            $("#select_word").append('<tr class = "select_tr">');
        }
        emotionArray.forEach(function(arrValue){
            let class_result = arrValue.classSearch(emotion);
            console.log(class_result);
            if(class_result != false){
                let label = $('<div/>', {
                    value : emotion,
                    id : "select"+idNum
                });
                label.addClass(class_result);
                label.addClass("emotion_card_text");
                label.html(emotion);
                checkedId.push("select"+idNum);
                console.log($("#select_word").children().last().append("<td>").children().last().append(label));
            }
        });
       

    }
    // 내가 선택한 감정단어 : 삭제
    function deleteEmotion(emotion){
        
        if($(emotion).prop('tagName')=="INPUT"){
            
            checkedId.forEach(function(value){
                         
                if($("#"+value).text()== emotion.val() && emotion.val() != ""){
                    emotion = $("#"+value);
                    console.log($("#"+value).text());
                    console.log(emotion.val());     
                    return false;
                }
            });
        }
        
        let last_node_count = $("#select_word").children().last().children().length;
        emotion.parent().remove();
        findRemove(emotion.text());
        if(last_node_count == 0)
            $("#select_word").children().last().remove();

        $("#select_word").children().each(function(index,item){
            let count = $(item).children().length;
            if(count < 5){
                let preLength = $(this).next().children().length;
                if(5-count <= preLength){
                    let needCount = 5-count;
                    $(this).next().children().each(function(index2,item2){
                        if(index2 >= needCount)
                            return false;
                        $(item2).insertAfter($(item).children().last());
                    });
                }
            }
           

        });

        
    }
    
    // 내가 선택한 감정단어 : 체크박스 해제
    function findRemove(emotion){
        
        emotionArray.forEach(function(value){

            value.id_name.forEach(function(nameValue){
                let search = $("#"+nameValue);
                if(search.val() == emotion){
                    search.prop('checked', false);
                }
            });
            
        });
        
    }
    

    $("input:radio[name=step6_emotion_category]").change(function()
    {
        $("#emotion_common").removeClass("animate_common");
        $("#emotion_thanks").removeClass("animate_thanks");
        $("#emotion_sorry").removeClass("animate_sorry");
        $("#emotion_celebration").removeClass("animate_celebration");
        $("#emotion_love").removeClass("animate_love");
        $("#emotion_say").removeClass("animate_say");

        $(".emotionSwiper").fadeIn(600);
        
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




function checkValue(num){
    for(let i =0; i<emotionArray.length; i++){
        $("input:checkbox[name="+emotionArray[i].class_name+"]").click(function(){
            let select = [];
            $("input[name="+emotionArray[i].class_name+"]:checked").each(function(index){
                select.push($(this).val());
            });
            
        });
    }
    
}

