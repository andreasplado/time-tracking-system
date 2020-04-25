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
        window.location.hash = $(this).attr( 'id' );
    });

    function activateTab() {
        //$( "[href]" ).removeClass( "cd-tabs__item--selected" );
        $( "[href='" + window.location.hash + "']" ).addClass( "cd-tabs__item--selected" );

    }

    $( window ).on( 'hashchange', function( e ) {
        activateTab();
    } );

    activateTab();







    function activateTab() {
        $( "[href='" + window.location.hash + "']" ).addClass( "cd-tabs__item--selected" );

    }


});