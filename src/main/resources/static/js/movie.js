// window.addEventlistener('load', () => {
//   document.querySelector('#jsMovie1').innerHTML = 
// `<li th:each="viewList :${viewsList3}" class="indexLoop">
//   <a href="" th:href="@{/video/{id}(id=${viewList.get(1)})}" class="loopLink">
//     <video th:src="${viewList.get(0)}" height="225px" width="400px" ></video>
//   </a>
// </li>`;

//   document.querySelector('#jsMovie2').innerHTML = 
//   `<video th:src="${movie.get(0)}" height="150px" width="300px" class="movieIndex" muted></video>
//   <div th:text="${movie.get(5)}" class="movieTitle"></div>`;
// });
$(document).ready(function(){
  $('#jsMovie1').innerHTML = 
`<li th:each="viewList :${viewsList3}" class="indexLoop">
  <a href="" th:href="@{/video/{id}(id=${viewList.get(1)})}" class="loopLink">
    <video th:src="${viewList.get(0)}" height="225px" width="400px" ></video>
  </a>
</li>`;

  $('#jsMovie2').innerHTML = 
  `<video th:src="${movie.get(0)}" height="150px" width="300px" class="movieIndex" muted></video>
  <div th:text="${movie.get(5)}" class="movieTitle"></div>`;
});