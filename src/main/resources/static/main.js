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

    console.log($('a[data-toggle="tab"]'));
    $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    var activeTab = localStorage.getItem('activeTab');

    console.log(activeTab);
    if(activeTab){
        $('#myTab a[href="' + activeTab + '"]').tab('show');
        console.log("Active tab" + activeTab);
    }

});