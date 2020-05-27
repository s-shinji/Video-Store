$(document).ready(function(){
  $(".movieIndex").mouseover(function() {
    $(this)[0].play();
    $(this)[0].currentTime = $(this)[0].initialTime || 0;
  }).mouseleave(function() {
    $(this)[0].pause();
  });

  $(".movieDetail").mouseover(function() {
    $(this).attr("controls", "");
  }).mouseleave(function() {
    $(this).removeAttr("controls");
  });

});
