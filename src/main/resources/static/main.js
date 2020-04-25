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

    window.localStorage;
    var tab = [];
    for(var i = 1; i<6; i++){
        tab[i] = document.getElementById("tab-" + i);

        /*$("#tab-" + i).click(function() {
          alert("#tab-" + i);
          // Store
          localStorage.setItem("tab", "tab-" + i);
        });*/
        tab[i].onclick = function(){
            localStorage.setItem("tab", "tab-" + i);
            console.log("tab-" + i);
        };
    }


    function defaultTab(){
        var defaultTabName = document.getElementById("tab-1");
        defaultTabName.className = "cd-tabs__item cd-tabs__item--selected";
    }

    function getTab(){
        var item = localStorage.getItem("tab");
        console.log(item);
        return item;
    }

    function activeTab(){
        console.log(getTab());
        if(getTab() == null|| getTab() == "undefined"){
            defaultTab();
        }else{
            var activatedTabName = document.getElementById(getTab());
            activatedTabName.class = "cd-tabs__item cd-tabs__item--selected";
        }
    }

    activeTab();




});