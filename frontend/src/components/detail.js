import React, { Component } from 'react';
import { connect } from 'react-redux';
import _ from 'lodash'
import HeaderA from './headerA';
import Review from './review';
import { readMovieDetail,deleteMovie } from '../actions' 
import { Link } from 'react-router-dom';
import { Field, reduxForm} from 'redux-form'

class Detail extends Component{

  componentDidMount () {
    const id = this.props.match.params.id
    const loginUserId = this.props.loginUserId
    this.props.readMovieDetail(id,loginUserId)
    // console.log(this.props)
  }
  // componentDidUpdate () {
  //   console.log("test")
  //   //profileが更新された際に返されるレスポンスでmovieステートを更新(そのため、レスポンスのstatusを指定することで200が返って来るはず)
  //   if(this.props.movie.status == 200) {
  //     const id = this.props.match.params.id
  //     const loginUserId = this.props.loginUserId
  //     this.props.readMovieDetail(id,loginUserId)
  //   }
  // }

  //Review情報に関する関数
  // renderReview() {
  //   const props = this.props
  //   const movieId = props.movie[1] ? props.movie[1].id : ""
  //   const style2 = {
  //     color:"#28a745"
  //   }

  //   {/* <!-- ログインユーザーと投稿者が等しいまたはログインしていない場合 --> */}
  //   if(props.movie[2] == movieId || props.movie[2] == 0){
  //     return(
  //       <div className="review">
  //         <div className="reviewIcon">
  //           <i className="far fa-grin-squint"></i>
  //           <span className="reviewCount">{props.movie[2] ? props.movie[2].good : ""}</span>
  //         </div>
  //         <div className="reviewIcon">
  //           <i className="far fa-smile"></i>
  //           <span className="reviewCount">{props.movie[2] ? props.movie[2].normal : ""}</span>
  //         </div>
  //         <div className="reviewIcon">
  //           <i className="far fa-sad-tear"></i>
  //           <span className="reviewCount">{props.movie[2] ? props.movie[2].bad : ""}</span>
  //         </div>
  //       </div>
  //     )
  //     {/* <!-- ログインユーザーと投稿者が等しくない場合はリンク化 --> */}
  //   } else if(props.movie[2] != movieId || props.movie[2] != 0) {
  //     return(
  //       <div className="review">
  //         <form name="formName1"action="@{/review/{id}(id=${movie.id})}" method="POST">
  //           {/* 以下のreview済みかどうかの分岐は、後で、reviewのコンポーネントを別途作成し呼び出す形にするかも？(matchReviewを取得するにはUserId情報が必要なため後回し) */}
  //           {/* <!-- 既にreview済みかどうかで分岐 --> */}
  //           <a if="${matchReview.equals('good')}"  className="reviewIcon reviewLink link1 ajax_btn1" style={style2}>
  //             <i className="far fa-grin-squint"></i>
  //             <span className="reviewCount1" text={props.movie[2] ? props.movie[2].good : ""}>0</span>
  //           </a>
  //           <a if="${!(matchReview.equals('good'))}"  className="reviewIcon reviewLink link1 ajax_btn1">
  //             <i className="far fa-grin-squint"></i>
  //             <span className="reviewCount1" text={props.movie[2] ? props.movie[2].good : ""}>0</span>       
  //           </a>
  //           <input type="hidden" name="review" value="good" className="sendReview1" />
  //         </form>

  //         <form name="formName2"action="@{/review/{id}(id=${movie.id})}" method="POST">
  //           {/* <!-- 既にreview済みかどうかで分岐 --> */}
  //           <a if="${matchReview.equals('normal')}"  className="reviewIcon reviewLink link2 ajax_btn2" style={style2}>
  //             <i className="far fa-smile"></i>
  //             <span className="reviewCount2" text={props.movie[2] ? props.movie[2].normal : ""}>0</span>
  //           </a>
  //           <a if="${!(matchReview.equals('normal'))}" className="reviewIcon reviewLink link2 ajax_btn2">
  //             <i className="far fa-smile"></i>
  //             <span className="reviewCount2" text={props.movie[2] ? props.movie[2].normal : ""}>0</span>
  //           </a>
  //           <input type="hidden" name="review" value="normal" className="sendReview2" />

  //         </form>

  //         <form name="formName3"action="@{/review/{id}(id=${movie.id})}" method="POST">
  //           {/* <!-- 既にreview済みかどうかで分岐 --> */}
  //           <a if="${matchReview.equals('bad')}" className="reviewIcon reviewLink link3 ajax_btn3" style={style2}>
  //             <i className="far fa-sad-tear"></i>
  //             <span className="reviewCount3" text={props.movie[2] ? props.movie[2].bad : ""}>0</span>
  //           </a>   
  //           <a if="${!(matchReview.equals('bad'))}" className="reviewIcon reviewLink link3 ajax_btn3">
  //             <i className="far fa-sad-tear"></i>
  //             <span className="reviewCount3" text={props.movie[2] ? props.movie[2].bad : ""}>0</span>
  //           </a>   
  //           <input type="hidden" name="review" value="bad" className="sendReview3" />

  //         </form>

  //       </div>
  //     )
  //   }
  // }
  async onSubmit(movieId) {
    let arrayDeleteInfo = []
    arrayDeleteInfo.push(movieId)
    arrayDeleteInfo.push(this.props.loginUserId)
    await this.props.deleteMovie(arrayDeleteInfo)
    await this.props.history.push("/index")
  }
  // 動画情報に関する関数
  renderDetail() {
    const props = this.props
    {/* avatarやnameの部分までが読み込まれる前にレンダリングされてしまうため、条件分岐を入れて読み込んでから処理を進めるようにしている？(https://qiita.com/uemuram/items/553b897913d44b92b70a) */}
    const movieId     = props.movie[1] ? props.movie[1].id : ""
    const userName    = props.movie[1] ? props.movie[1].user.name : ""
    const avatar      = props.movie[1] ? props.movie[1].user.avatar : ""
    const movieUserId = props.movie[1] ? props.movie[1].user.id : ""
    const views       = props.movie[1] ? props.movie[1].views : ""
    const loginUserId   = props.loginUserId
    const {handleSubmit} = props
    const style = {
      display:"block"
    }
    const style3 = {
      marginTop: "60px",
      height:"92vh"
    }
    return (
    <div role="main" className="mainBackground" style={style3}>

      <div className="detail">
        {/* <!-- props.movie[0]に動画のsrc情報が入っている --> */}
        <video src={props.movie[0]} height="500px" width="100%" className="movieDetail" controls></video>

        <div className="detailInfo" >
          <div>
            {/* <!-- props.movie[1]に動画やユーザーに関する情報が入っている --> */}
            <Link  to={`/user/${movieUserId}`} className="postUserName" style={style}>
              {/* <span>{this.state.detailMovieInfo[0]}</span> */}
              {/* avatarやnameの部分までが読み込まれる前にレンダリングされてしまうため、条件分岐を入れて読み込んでから処理を進めるようにしている？(https://qiita.com/uemuram/items/553b897913d44b92b70a) */}
              <img src={avatar} className="upAvatar" height="50px" width="50px" />
              <span id="postUser">投稿者: {userName}</span>
            </Link>
          </div>

          <div>
            <span id="views">{`再生回数: ${views}回`}</span>
          </div>
          {/* Review情報に関する関数呼び出し */}
          {/* {this.renderReview()} */}
          <Review />

        </div>

        {/* <!-- 現在ログイン中のユーザーと動画投稿者が同じな場合、削除ボタンを表示する --> */}
        {loginUserId == movieUserId &&
          <form onSubmit={handleSubmit(() => this.onSubmit(movieId))}>
            {/* <!-- このname属性が@RequestParamで受け取る際のキーになる（受け取る値はvalue属性） --> */}
            <input type="hidden" name="movieId" value={movieId} />
            <input type="submit" value="削除" className="deleteBtn" />
          </form>   
        } 

      </div>
    </div>
       
    )

  }
 
  render() {
    return (
      <React.Fragment>
        <HeaderA />
        {this.renderDetail()}
      </React.Fragment>
    );
  }
}

const mapStateToProps = state => ({movie : state.movie, loginUserId : state.auth})
const mapDispatchToProps = ({ readMovieDetail,deleteMovie })
export default connect(mapStateToProps, mapDispatchToProps)(reduxForm({form: 'deleteMovieForm2'})(Detail));

