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
    for(var i = 1; i<6; i++){
        tab[i] = document.getElementById("tab-" + i);

        $("#tab-" + i).click(function() {
          alert( "Handler for .click() called." );
          // Store
          localStorage.setItem("tab", "tab-" + i);
        });
    }

    function defaultTab(){
        var defaultTabName = document.getElementById("tab-1");
        defaultTabName.className = "cd-tabs__item cd-tabs__item--selected";
    }

    function getTab(){
        return localStorage.getItem("tab");
    }

    function activeTab(){
        if(getTab() == null|| getTab() == "undefined"){
            defaultTab();
        }else{
            var activatedTabName = document.getElementById(getTab());
            activatedTabName.class = "";
            activatedTabName.classList.add("cd-tabs__item--selected");
        }
    }

    activeTab();




});