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


    $( '.cd-tabs__item' ).on( 'click', function(){
        window.location.hash = $(this).attr( 'href' );
    });
    // Check if we need to activate a tab based on the has
    if ( window.location.hash && $( window.location.hash ).length ) {
        $( window.location.hash ).addClass( "cd-tabs__item--selected" );
    }


});