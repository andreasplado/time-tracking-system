$(document).ready(function(){

    /*$("#search-by-date-range-and-username").submit(function(event){
        alert("lol");
        event.preventDefault();
    });*/
    $("#workhour-form").submit(function(event){
        if($.trim($("#notes").val()) === ""){
            $("#notes").val("No notes");
        }
    });

    var tab = [];
    for(int i = 1; i<6; i++){
        tab[i] = document.getElementById("tab-" + i);
        $("#tab-" + i).click(function() {
          alert( "Handler for .click() called." );
        });
    }




});