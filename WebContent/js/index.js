$('.panel').click(function() {
  if(!$(this).hasClass('active')) {
    var index = $(this).index();
    $('#order').removeClass();
    $('#order').addClass('opt'+(index+1));
    $('#choice').get(0).selectedIndex = index;
    $(this).siblings().addClass('hidden');
    $(this).addClass('active');
    $('#order').delay(800).slideToggle(400);
  }
});

$('#back').click(function() {
  $('#order').slideToggle(400);
  var self = this;
  setTimeout(function() {
    $('.panel').removeClass('hidden active');
  }, 400);
});

$('#quantity').on('input change', function() {
  var qv = $('#quantity').val();
  if(qv % 1 != 0) {
    qv = parseInt(qv, 10);
    if(qv == 0) qv = "";
    qv += "½";
  }
  $('label[for="quantity"]').text(qv);
  // TODO: update the price as well
})