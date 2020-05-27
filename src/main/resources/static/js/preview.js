$(document).ready(function(){
  const buildFileField = function(index,src) {
      const html = `<video id="thumbnail_${index}" src="${src}" class="thumbnail" controls>
                  </video>`;
      // const html = `<img id="thumbnail_${index}" src="${src}" class="thumbnail image" height="300px" width="300px" controls>`;
      return html;
  }

  $('#upfile').change(function(){

      $('.thumbnail').remove();
      for(let i = 0; i < this.files.length; i++) {
          // 選択されたファイル情報を取得
          var file = this.files[i];
          
          // readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
          var reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = function(e) {
              //filesが複数枚ある場合にはresultではなくe.target.resultを用いる
              $('#preview').append(buildFileField(i, e.target.result));
          }
      }

      $('.uploadError').remove();

  });
});
