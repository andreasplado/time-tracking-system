var moment = rome.moment;

rome(start_time_range, {dateValidator: rome.val.beforeEq(end_time_range), inputFormat: "YYYY-MM-DD HH:mm"} );
rome(end_time_range, {dateValidator: rome.val.afterEq(start_time_range), inputFormat: "YYYY-MM-DD HH:mm"} );

var picker = rome(ind);

if (toggle.addEventListener) {
  toggle.addEventListener('click', toggler);
} else if (toggle.attachEvent) {
  toggle.attachEvent('onclick', toggler);
} else {
  toggle.onclick = toggler;
}

function toggler () {
  if (picker.destroyed) {
    picker.restore();
  } else {
    picker.destroy();
  }
  toggle.innerHTML = picker.destroyed ? 'Restore <code>rome</code> instance!' : 'Destroy <code>rome</code> instance!';
}


rome(iwe, {
  dateValidator: function (d) {
    return moment(d).day() !== 6;
  }
});

rome(win, {
  dateValidator: function (d) {
    var m = moment(d);
    var y = m.year();
    var f = 'MM-DD';
    var start = moment('12-21', f).year(y).startOf('day');
    var end = moment('03-19', f).year(y).endOf('day');
    return m.isBefore(start) && m.isAfter(end);
  }
});

rome(tim, {
  timeValidator: function (d) {
    var m = moment(d);
    var start = m.clone().hour(12).minute(59).second(59);
    var end = m.clone().hour(18).minute(0).second(1);
    return m.isAfter(start) && m.isBefore(end);
  }
});

rome(inl).on('data', function (value) {
  inlv.innerText = inlv.textContent = value;
});

rome(left, {
  time: false,
  dateValidator: rome.val.beforeEq(right)
});

rome(right, {
  time: false,
  dateValidator: rome.val.afterEq(left)
});

rome(leftInline, {
  time: false,
  dateValidator: rome.val.beforeEq(rightInline)
});

rome(rightInline, {
  time: false,
  dateValidator: rome.val.afterEq(leftInline)
});

rome(exa, {
  dateValidator: rome.val.except('2014-08-01')
});

rome(exb, {
  dateValidator: rome.val.except('2014-08-02', '2014-08-06')
});

rome(exc, {
  dateValidator: rome.val.except(['2014-08-04', '2014-08-09'])
});

rome(exd, {
  dateValidator: rome.val.except([['2014-08-03', '2014-08-07'], '2014-08-15'])
});

rome(exe, {
  dateValidator: rome.val.only([
    ['2014-08-01', '2014-08-15'], '2014-08-22'
  ])
});

rome(exf, {
  dateValidator: rome.val.except([exb, exd, '2014-08-15'])
});

