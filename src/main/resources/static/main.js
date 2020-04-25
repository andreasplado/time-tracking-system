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
    if (sessionStorage.getItem('lastsessionid') != null){
       //go to anchor, simulates the link click
       $(document).scrollTop( $(sessionStorage.getItem('lastsessionid')).offset().top );
    }
    $('a[href=#tab-dashboard*]').on('click', function(){
       sessionStorage.setItem('lastsessionid', this.href);
    });
    $('a[href=#tab-workhours]').on('click', function(){
       sessionStorage.setItem('lastsessionid', this.href);
    });
    $('a[href=#tab-my-profile]').on('click', function(){
      sessionStorage.setItem('lastsessionid', this.href);
    });
    $('a[href=#tab-admin]').on('click', function(){
        sessionStorage.setItem('lastsessionid', this.href);
    });
  $('a[href=#tab-company]').on('click', function(){
      sessionStorage.setItem('lastsessionid', this.href);
  });

});