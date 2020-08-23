import React, { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm} from 'redux-form'
import {postReview} from  '../actions'
import { withRouter } from 'react-router';

class Review extends Component {

  async onSubmit(review) {
    let hashReviewInfo = {}
    hashReviewInfo.review = review
    hashReviewInfo.movieId = this.props.movie[1].id
    hashReviewInfo.loginUserId = this.props.loginUserId
    await this.props.postReview(hashReviewInfo)
  }

  renderReview() {
    const props = this.props
    const movieId = props.movie[1] ? props.movie[1].id : ""
    const movieUserId = props.movie[1] ? props.movie[1].user.id : ""
    const loginUserId = props.loginUserId
    const myReview = props.movie[2] ? props.movie[2] : ""
    const reviewNum = props.movie[3] ? props.movie[3] : ""
    // const {handleSubmit} = props

    const style2 = {
      color:"#28a745"
    }

    {/* <!-- ログインユーザーと投稿者が等しいまたはログインしていない場合 --> */}
    if(loginUserId == movieUserId || loginUserId == 0){
      return(
        <div className="review">
          <div className="reviewIcon">
            <i className="far fa-grin-squint"></i>
            <span className="reviewCount">{reviewNum.good}</span>
          </div>
          <div className="reviewIcon">
            <i className="far fa-smile"></i>
            <span className="reviewCount">{reviewNum.normal}</span>
          </div>
          <div className="reviewIcon">
            <i className="far fa-sad-tear"></i>
            <span className="reviewCount">{reviewNum.bad}</span>
          </div>
        </div>
      )
      {/* <!-- ログインユーザーと投稿者が等しくない場合はリンク化 --> */}
    } else if(loginUserId != movieId && loginUserId != 0) {
      return(
        <div className="review">
          {/* <!-- 既にreview済みかどうかで分岐 --> */}
          {/* 三項演算子でequals文がうまくいかないため「==」で代用 */}
          {myReview == "good" ? 
            <div className="reviewIcon reviewLink link1 ajax_btn1" style={style2}>
              <i className="far fa-grin-squint"></i>
              <span className="reviewCount1">{reviewNum.good}</span>
            </div>
          :
            <div name="formName1" onClick={() => this.onSubmit('good')}>
              <a className="reviewIcon reviewLink link1 ajax_btn1">
                <i className="far fa-grin-squint"></i>
                <span className="reviewCount1">{reviewNum.good}</span>       
              </a>
              {/* <input type="hidden" name="review" value="good" className="sendReview1" /> */}
            </div>
          }

          {/* <!-- 既にreview済みかどうかで分岐 --> */}
          {myReview == "normal" ? 
            <div className="reviewIcon reviewLink link2 ajax_btn2" style={style2}>
              <i className="far fa-smile"></i>
              <span className="reviewCount2">{reviewNum.normal}</span>
            </div>
          :
            <div name="formName2" onClick={() => this.onSubmit('normal')}>
              <a className="reviewIcon reviewLink link2 ajax_btn2">
                <i className="far fa-smile"></i>
                <span className="reviewCount2">{reviewNum.normal}</span>
              </a>
              {/* <input type="hidden" name="review" value="normal" className="sendReview2" /> */}
            </div>
          }

          {/* <!-- 既にreview済みかどうかで分岐 --> */}
          {myReview == "bad" ? 
            <div if="${matchReview.equals('bad')}" className="reviewIcon reviewLink link3 ajax_btn3" style={style2}>
              <i className="far fa-sad-tear"></i>
              <span className="reviewCount3">{reviewNum.bad}</span>
            </div>
          :
            <div name="formName3" onClick={() => this.onSubmit('bad')}>
              <a className="reviewIcon reviewLink link3 ajax_btn3">
                <i className="far fa-sad-tear"></i>
                <span className="reviewCount3">{reviewNum.bad}</span>
              </a>   
              {/* <input type="hidden" name="review" value="bad" className="sendReview3" /> */}
            </div>
            }
        </div>
      )
    }
  }

  render() {
    return(
      <React.Fragment>
        {this.renderReview()}
      </React.Fragment>
    )
    }
}
const mapStateToProps = state => ({movie : state.movie,loginUserId : state.auth})
const mapDispatchToProps  = ({postReview})
export default withRouter(connect(mapStateToProps,mapDispatchToProps)(reduxForm({form: "reviewForm"})(Review)));