var start_time_picker = new MaterialDatetimePicker({
    el: document.querySelector('#start_time'),
    format: 'YYYY-MM-DD HH:mm:ss',
    default: moment()
});

var end_time_picker = new MaterialDatetimePicker({
    el: document.querySelector('#end_time'),
    format: 'YYYY-MM-DD HH:mm:ss',
    default: moment()
});

var start_time = document.querySelector('#start_time');
var end_time = document.querySelector('#end_time');

start_time.addEventListener('click', function() {
  start_time_picker.open();
  console.log("start time selected");
}, false);

end_time.addEventListener('click', function() {
  end_time_picker.open();
  console.log("end time selected");
}, false);