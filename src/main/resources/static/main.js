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
    /*for(var i = 1; i<6; i++){
        tab[i] = document.getElementById("tab-" + i);

        $("#tab-" + i).click(function() {
          alert("#tab-" + i);
          // Store
          localStorage.setItem("tab", "tab-" + i);
        });
        tab[i].onclick = function(){
            localStorage.setItem("tab", "tab-" + i);
            console.log("tab-" + i);
        };
    }*/


    $("#tab-1").click(function() {
      window.localStorage.setItem("tab", "tab-1");
    });
    $("#tab-2").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-2");
    });
    $("#tab-3").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-3");
    });
    $("#tab-4").click(function() {
      window.localStorage.setItem("tab", "tab-4");
    });
    $("#tab-5").click(function() {
      window.localStorage.setItem("tab", "tab-5");
    });


    function defaultTab(){
        var defaultTabName = document.getElementById("tab-1");
    }

    function getTab(){
        var item = window.localStorage.getItem("tab");
        console.log(item);
        return item;
    }

    function activeTab(){
        console.log(getTab());
        if(false){
            defaultTab();
        }else{
            //var activatedTabName = document.getElementById("" + getTab());
            $( "#" + getTab() ).addClass( "cd-tabs__item--selected" );
        }
    }

    activeTab();




});