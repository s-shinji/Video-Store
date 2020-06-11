$(document).ready(function(){
  $(".followingInfo").click(function() {
    $("#followingModal").fadeIn();
  });

  $(".followerInfo").click(function() {
    $("#followerModal").fadeIn();
  });

  $(".closeModal").click(function() {
    $(".modalBackground").fadeOut();
  })
});