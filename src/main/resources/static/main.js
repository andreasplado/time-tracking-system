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

    function activateTab() {
        $( "[href='" + window.location.hash + "']" ).addClass( "cd-tabs__item--selected" );

    }

    $( window ).on( 'hashchange', function( e ) {
        activateTab();
    } );

    activateTab();


});