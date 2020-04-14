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

});

moment.locale('fr');
console.log(moment(1316116057189).fromNow());
console.log(moment.locales());
const picker = new MaterialDatePicker().
on('submit', val => {
  events.innerHTML += val.toDate() + '\n';
}).
on('open', () => {
  events.innerHTML += 'open\n';
}).
on('close', () => {
  events.innerHTML += 'close\n';
});
