$(document).ready(function(){
//   $(".movieIndex").mouseover(function() {
//     $("#hoverChange").html(
      
//       <video th:src="${movie.get(0)}" height="150px" width="300px" class="movieIndex" muted></video>
//     )
//     $(this)[0].play();
//     $(this)[0].currentTime = $(this)[0].initialTime || 0;
//   }).mouseleave(function() {
//     $(this)[0].pause();
//   });

//   $(".movieDetail").mouseover(function() {
//     $(this).attr("controls", "");
//   }).mouseleave(function() {
//     $(this).removeAttr("controls");
//   });

  $(".thumbnailBox").mouseover(function() {
    var thumbnail = $(this).attr("id")
    console.log(thumbnail)
    $(`#${thumbnail}`).append("<i class='fab fa-youtube'></i>");
    $(`#${thumbnail}`).css("opacity","0.8");
  }).mouseleave(function(){
    var thumbnail = $(this).attr("id")
    $(".fa-youtube").remove();
    $(`#${thumbnail}`).css("opacity","1.0");
  });
});
