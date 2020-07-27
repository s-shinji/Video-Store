import React, {Component} from 'react';
// import {connect} from 'react-redux';
// import {postMovie} from '../actions'


class Upload extends Component {
  constructor(props) {
    super(props)
    this.state={
      imageSrc: null,
      movieSrc: null
    }
    // this.onSubmit = this.onSubmit.bind(this)
    this.handleChangeFile1 = this.handleChangeFile1.bind(this)
    this.handleChangeFile2 = this.handleChangeFile2.bind(this)
  }


  componentDidMount () {
    // const URL = 'http://localhost:8080/upload'
    // fetch(URL, {mode: 'cors'})
    // .then(res => 
    //   res.json())
    // .then(data => {
    //   this.setState({
    //     loginUser:data[0],
    //   })

    // });
  }

  // async onSubmit(values) {
  //   console.log(values)
  //   await this.props.postMovie(values)
  //   this.props.history.push('/index')
  // }

  handleChangeFile1(e) {
    const createObjectURL = (window.URL || window.webkitURL).createObjectURL || window.createObjectURL;
    // ①イベントからfileの配列を受け取る
    const files = e.target.files;
    console.log(files)

    // ②createObjectURLで、files[0]を読み込む
    const image_url = createObjectURL(files[0]);
    console.log(image_url)
    // ③setStateする！
    this.setState({imageSrc: image_url});
  }

  handleChangeFile2(e) {
    const createObjectURL = (window.URL || window.webkitURL).createObjectURL || window.createObjectURL;
    // ①イベントからfileの配列を受け取る
    const files = e.target.files;

    // ②createObjectURLで、files[0]を読み込む
    const movie_url = createObjectURL(files[0]);

    // ③setStateする！
    this.setState({movieSrc: movie_url});
  }


  render() {

    const style = {
      display: "block",
      width: "500px"
    }
    // console.log(this.state.imageSrc)

    return(
      <form action="/upload" method="post" encType="multipart/form-data" className="uploadForm">
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
                    <div className="uploadParts"><span>サムネイル</span></div>
                    <i className="far fa-image"></i>
                </label>

                {/* <div className="uploadError" errors="*{movie}" style="color:red;"></div> */}
                <label htmlFor="upfile" className="uploadBox">
                    <input type="file" name="movie"  id ="upfile" accept="video/*" capture="camera" onChange={this.handleChangeFile2}/>
                    <div className="uploadParts"><span>動画</span></div>
                    <i className="fas fa-video"></i>
                </label>
            </div>
            
            {/* <div if="${#fields.hasErrors('title')}" errors="*{title}" style="color:red;"></div> */}
            <div className="uploadTitle">
                <span style={style}>タイトル:</span>
                <input type="text" name="title"  placeholder="１〜５０字以内" style={style} required />
            </div>

            <br /> <input className="uploadSubmit"type="submit" value="送信する"/>
        </div>
      </form>
  
    )
  }

      
}
// const mapStateToProps
// const mapDispatchToProps = ({postMovie})
// export default connect(null, mapDispatchToProps)(Upload);
export default Upload;


