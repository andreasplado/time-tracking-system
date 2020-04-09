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

});

window.fitText( document.getElementById("tab-text1") );
window.fitText( document.getElementById("tab-text2") );
window.fitText( document.getElementById("tab-text3") );
window.fitText( document.getElementById("tab-text4") );


