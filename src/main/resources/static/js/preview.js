$(document).ready(function(){
  const buildFileField = function(index,src) {
      const html = `<video id="movie_${index}" src="${src}" class="movie"  controls>
                  </video>`;
      // const html = `<img id="thumbnail_${index}" src="${src}" class="thumbnail image" height="300px" width="300px" controls>`;
      return html;
  }
  const buildFileField2 = function(index,src) {
      const html = `<img id="thumbnail_${index}" src="${src}" class="thumbnail" controls>`;
      return html;
  }

  //upload.htmlの内容
  $('#upfile').change(function(){

      $('.movie').remove();
    //   for(let i = 0; i < this.files.length; i++) {
          // 選択されたファイル情報を取得
          var file = this.files[0];
          
          // readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
          var reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = function(e) {
              //filesが複数枚ある場合にはresultではなくe.target.resultを用いる
              $('#preview').append(buildFileField(0,e.target.result));
          }
    //   }

      $('.uploadError').remove();

  });

  $('#upfile2').change(function(){

      $('.thumbnail').remove();
    //   for(let i = 0; i < this.files.length; i++) {
          // 選択されたファイル情報を取得
          var file = this.files[0];
          
          // readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
          var reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = function(e) {
              //filesが複数枚ある場合にはresultではなくe.target.resultを用いる
              $('#preview2').append(buildFileField2(0,e.target.result));
          }
    //   }

  });

  //user.htmlの内容
  $('#upAvatar').change(function() {
    var file = this.files[0];
    // console.log(file);
    // readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        //filesが複数枚ある場合にはresultではなくe.target.resultを用いる
        console.log(e.target.result);
        $('.upAvatarBox img').attr('src', e.target.result);
    }

  });
});
