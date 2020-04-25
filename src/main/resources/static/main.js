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


    //TABS
    var $tabs = $('.cd-tabs__list'),
        $tabsA = $tabs.find('a'),
        $tabsC = $('.tab_content'),
        start = window.location.hash || '#welcome';

    function deactivate() {
        $tabsA.removeClass('cd-tabs__item--selected');
        $tabsC.hide();
    }
    function activate(href) {
        $tabsA.filter('[href=' + href + ']').addClass('cd-tabs__item--selected');
        $(href).fadeIn();
    }

    function clicked(e) {
        var href = $(e.target).attr('href');
        deactivate();
        activate(href);
    }

    deactivate();
    activate(start);
    $tabs.on('click', 'a', clicked);

});