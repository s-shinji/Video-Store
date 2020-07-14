import React from 'react';
import { Link } from 'react-router-dom';


const Upload = () => {
      const style = {
        display: "block",
        width: "500px"
      }
      return(
        <form action="/upload" object="${movieForm}" method="post" encType="multipart/form-data" className="uploadForm">
          <div className="previewBox">
              <div id="preview2"></div>
              <div id="preview"></div>
          </div>
          <div className="uploadFlex">
              <div className="uploadFlex2">
                  <label htmlFor="upfile2" className="uploadBox">
                      <input type="file" field="*{thumbnail}" id ="upfile2" accept="image/*" capture="camera"/>
                      <div className="uploadParts"><span>サムネイル</span></div>
                      <i className="far fa-image"></i>
                  </label>

                  {/* <div className="uploadError" errors="*{movie}" style="color:red;"></div> */}
                  <label htmlFor="upfile" className="uploadBox">
                      <input type="file" field="*{movie}"  id ="upfile" accept="video/*" capture="camera"/>
                      <div className="uploadParts"><span>動画</span></div>
                      <i className="fas fa-video"></i>
                  </label>
              </div>
              
              {/* <div if="${#fields.hasErrors('title')}" errors="*{title}" style="color:red;"></div> */}
              <div className="uploadTitle">
                  <span style={style}>タイトル:</span>
                  <input type="text" field="*{title}"  placeholder="１〜５０字以内" style={style} required />
              </div>

              <br /> <input className="uploadSubmit"type="submit" value="送信する" />
          </div>
        </form>
    
      )
      
}

export default Upload;


