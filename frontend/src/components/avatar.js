import React, { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm} from 'redux-form'
import {postProfile, postFollow, postUnfollow} from  '../actions'
import { withRouter } from 'react-router';
// import { Redirect } from 'react-router-dom';





class Avatar extends Component {
  constructor(props) {
    super(props)
    this.handleAccessMyPage = this.handleAccessMyPage.bind(this)
    this.handleAccessOtherUserPage = this.handleAccessOtherUserPage.bind(this)
    this.follow = this.follow.bind(this)
    this.unfollow = this.unfollow.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
  }

  async follow() {
    let hashFollowInfo = {}
    hashFollowInfo.userId = this.props.user[2].id
    hashFollowInfo.loginUserId = this.props.loginUserId
    await this.props.postFollow(hashFollowInfo)
  }
  async unfollow() {
    let hashUnfollowInfo = {}
    hashUnfollowInfo.userId = this.props.user[2].id
    hashUnfollowInfo.loginUserId = this.props.loginUserId
    await this.props.postUnfollow(hashUnfollowInfo)
  }
  async onSubmit() {
    let hashUpProfileInfo = {}
    hashUpProfileInfo.avatar = document.getElementById("upAvatar").files[0]
    hashUpProfileInfo.id = this.props.user[2].id
    await this.props.postProfile(hashUpProfileInfo)
    await this.props.history.push(`/user/${this.props.user[2].id}`)
  }

  async onChange() {
    document.getElementById('avatarUpdateBtn').disabled = false;
  }

  handleAccessMyPage(){
    const style = {
      display:"none"
    }

    const user = this.props.user
    const loginUserId = this.props.loginUserId
    const movieUserId = user[2] ? user[2].id : ""

    const {handleSubmit} = this.props
  
    if(movieUserId == loginUserId) {
      return(
        //ログインユーザーが自らのユーザーページを訪れた場合
        <div>
          <form encType="multipart/form-data" onSubmit={handleSubmit(this.onSubmit)}>
  
            <label htmlFor="upAvatar" className="upAvatarBox">
              <img src="" src={user[2] ? user[2].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
              <input type="file" id="upAvatar" style={style} name="avatar"  accept="image/*" onChange={this.onChange}/> 
              {/* <Field type="file" id="upAvatar" style={style} name="avatar"  accept="image/*" component={this.renderField}/>  */}
            </label>
            <input type="submit" value="更新" className="deleteBtn"  id="avatarUpdateBtn" disabled={true}/>
          </form>
        </div>
      )
    }
  }
  
  handleAccessOtherUserPage() {
    const user = this.props.user
    const loginUserId = this.props.loginUserId
    const movieUserId = user[2] ? user[2].id : ""
    const {handleSubmit} = this.props

    if(movieUserId != loginUserId) {
      return(
        // ログインユーザーが他のユーザーページを訪れた場合
        <div>
          <label htmlFor="upAvatar" className="upAvatarBox">
            <img src="" src={user[2] ? user[2].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
          </label>
          {loginUserId != 0 ?
            <div className="followBox">
              { user[3] == false ? <button className="followBtn" onClick={handleSubmit(this.follow)}> フォローする </button> : ""}
              { user[3] == true ? <button className="followedBtn" onClick={handleSubmit(this.unfollow)}> フォロー中 </button> : "" }
            </div>
          : "" }
        </div>
      )
    }
  }
  
  render() {
    return(
      <React.Fragment>
        {this.handleAccessMyPage()}
        {this.handleAccessOtherUserPage()}
      </React.Fragment>
    )
    }
}
const mapStateToProps = state => ({user : state.user,loginUserId : state.auth})
const mapDispatchToProps  = ({postProfile,postFollow,postUnfollow})
export default withRouter(connect(mapStateToProps,mapDispatchToProps)(reduxForm({form: "profileForm"})(Avatar)));