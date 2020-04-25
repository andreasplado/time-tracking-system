$(document).ready(function(){

    $("#workhour-form").submit(function(event){
        if($.trim($("#notes").val()) === ""){
            $("#notes").val("No notes");
        }
    });
    /*
    $("#tab-1").click(function() {
      window.localStorage.setItem("tab", "tab-1");
      window.localStorage.setItem("content", "tab-dashboard");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-2").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-2");
      window.localStorage.setItem("content", "tab-workhours");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-3").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-3");
      window.localStorage.setItem("content", "tab-my-profile");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-4").click(function() {
      window.localStorage.setItem("tab", "tab-4");
      window.localStorage.setItem("content", "tab-admin");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-5").click(function() {
      window.localStorage.setItem("tab", "tab-5");
      window.localStorage.setItem("content", "tab-company");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });


    function defaultTab(){
        var defaultTabName = document.getElementById("tab-1");
    }

    function getTab(){
        var item = window.localStorage.getItem("tab");
        console.log(item);
        return item;
    }

    function getContent(){
        var item = window.localStorage.getItem("content");
        console.log(item);
        return item;
    }

    function activeTab(){
        console.log(getTab());
        if(false){
            defaultTab();
        }else{
            //var activatedTabName = document.getElementById("" + getTab())
            $( "#" + getTab() ).addClass( "cd-tabs__item--selected" );
            $( "#" + getContent() ).addClass( "cd-tabs__panel--selected" );
            window.localStorage.removeItem("content");
            window.localStorage.removeItem("tab");
        }
    }

    activeTab();
    */



});