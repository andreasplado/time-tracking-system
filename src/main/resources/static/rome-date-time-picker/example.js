var moment = rome.moment;

rome(start_time, {dateValidator: rome.val.beforeEq(end_time),
inputFormat: "YYYY-MM-DD HH:mm:ss", autoHideOnClick: false});
    //dateValidator: rome.val.beforeEq(end_time)});
rome(end_time, {dateValidator: rome.val.afterEq(start_time),
inputFormat: "YYYY-MM-DD HH:mm:ss", autoHideOnClick: false});
rome(lunch_time, {inputFormat: "HH:mm:ss", date: false});
rome(start_time_range, {dateValidator: rome.val.beforeEq(end_time_range), inputFormat: "YYYY-MM-DD HH:mm:ss"} );
rome(end_time_range, {dateValidator: rome.val.afterEq(start_time_range), inputFormat: "YYYY-MM-DD HH:mm:ss"} );

