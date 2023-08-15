// 참고하려고 다른 프로젝트의 파일을 불러왔습니다. 이 프로젝트와 관련이 없습니다.

var load=null;

$(document).ready(function(){
    load=function(){
        $.get('/load', function(data){
            $("#add_here").empty();
            
            $(data).each(function(i){
                var id=this._id;

                $("#add_here").append("<tr class='"+id+"'> <td>"+this.word+"</td> <td>"+this.meanings+"</td> </tr>");

                $("#add_here tr:last-child").append("<td><button class='delete_data_bu'>삭제</button></td>");

                $("#add_here tr:last-child .delete_data_bu").click(function(evt){
                    del(id)
                });
            });
        });
    };
    // $("#words_input button").click(function(evt){
    //     contents=$("#words_input textarea").val().split(/r?\n/);
    //     wrong_form="";
    //     for (var i=0; i<contents.length; i++) {
    //         content=contents[i].split('&&');
    //         if(content.length!=2){
    //             wrong_form+=contents[i]+'\n';
    //         }
    //         else{
    //             write(content[0], content[1].split(","));
    //         }
    //     }
    //     $("#words_input textarea").val("");
    //     if(wrong_form.length!=0){
    //         alert(wrong_form+'은 잘못된 형식의 입력입니다.');
    //     }
    // });
});