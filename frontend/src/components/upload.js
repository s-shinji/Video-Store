import React, {Component} from 'react';
import {connect} from 'react-redux';
import {reduxForm} from 'redux-form'
import {postMovie} from '../actions'
import HeaderA from './headerA';



class Upload extends Component {
  constructor(props) {
    super(props)
    this.state={
      imageSrc: null,
      movieSrc: null
    }
    this.handleChangeFile1 = this.handleChangeFile1.bind(this)
    this.handleChangeFile2 = this.handleChangeFile2.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
  }

  handleChangeFile1(e) {
    const createObjectURL = (window.URL || window.webkitURL).createObjectURL || window.createObjectURL;
    // ①イベントからfileの配列を受け取る
    const files = e.target.files;

    // ②createObjectURLで、files[0]を読み込む
    const image_url = createObjectURL(files[0]);
    // ③setStateする！
    this.setState({imageSrc: image_url});
  }

  handleChangeFile2(e) {
    document.getElementById("uploadSubmitBtn").disabled = false

    const createObjectURL = (window.URL || window.webkitURL).createObjectURL || window.createObjectURL;
    // ①イベントからfileの配列を受け取る
    const files = e.target.files;

    // ②createObjectURLで、files[0]を読み込む
    const movie_url = createObjectURL(files[0]);

    // ③setStateする！
    this.setState({movieSrc: movie_url});
  }

  async onSubmit(e) {
    e.preventDefault()
    let hashUpfileInfo = {}

    //ファイルがundefinedだとサーバとの接続がうまくいかないため、サムネイル画像の選択がなかった場合には動画と同じファイルを代入しサーバ側で分岐処理を行う
    hashUpfileInfo.thumbnail=document.getElementById("upfile2").files[0] ? document.getElementById("upfile2").files[0] : document.getElementById("upfile").files[0]
    hashUpfileInfo.movie=document.getElementById("upfile").files[0]
    hashUpfileInfo.title=document.getElementById("upfileTitle").value
    hashUpfileInfo.id=document.getElementById("upfileId").value

    await this.props.postMovie(hashUpfileInfo)
    await this.props.history.push('/index')

  }

  render() {
    // const {handleSubmit} = this.props
    const loginUserId = this.props.loginUserId
    const style = {
      display: "block",
      width: "500px"
    }

    return(
      <React.Fragment>
        <HeaderA />
        <form encType="multipart/form-data" className="uploadForm" onSubmit={this.onSubmit}>
          <div className="previewBox">
              <div id="preview">
                {this.state.imageSrc ? <img id="thumbnail_${index}" src={this.state.imageSrc} className="thumbnail" controls /> : ""}
              </div>
              <div id="preview2">
                {this.state.movieSrc ? <video id="movie_${index}" src={this.state.movieSrc} className="movie"  controls /> : ""}
              </div>
          </div>
          <div className="uploadFlex">
              <div className="uploadFlex2">
                  <label htmlFor="upfile2" className="uploadBox">
                      <input type="file" name="thumbnail" id ="upfile2" accept="image/*" capture="camera" onChange={this.handleChangeFile1}/>
                      {/* <Field type="file" name="thumbnail" id ="upfile2" accept="image/*" capture="camera" onChange={this.handleChangeFile1} component={this.renderField}/> */}
                      <div className="uploadParts"><span>サムネイル</span></div>
                      <i className="far fa-image"></i>
                  </label>

                  <label htmlFor="upfile" className="uploadBox">
                      <input type="file" name="movie"  id ="upfile" accept="video/*" capture="camera" onChange={this.handleChangeFile2}/>
                      {/* <Field type="file" name="movie"  id ="upfile" accept="video/*" capture="camera" onChange={this.handleChangeFile2} component={this.renderField} /> */}
                      <div className="uploadParts"><span>動画</span></div>
                      <i className="fas fa-video"></i>
                  </label>
              </div>
              
              <div className="uploadTitle">
                  <span style={style}>タイトル:</span>
                  <input type="text" name="title"  id="upfileTitle" placeholder="１〜５０字以内" style={style} required />
                  {/* <Field type="text" name="title"  placeholder="１〜５０字以内" style={style} required component={this.renderField}/> */}
              </div>
              <div className="uploadId">
                <input type="hidden" name="loginUserId" id="upfileId" value={loginUserId}/>
              </div>

              <br /> <input className="uploadSubmit" id="uploadSubmitBtn" type="submit" value="送信する" disabled={true}/>
          </div>
        </form>
      </React.Fragment>
  
    )
  }

      
}
const mapStateToProps = state => ({loginUserId : state.auth})
const mapDispatchToProps = ({postMovie})
export default connect(mapStateToProps, mapDispatchToProps)(reduxForm({ form: 'uploadForm'})(Upload));


