import React, {Component} from 'react';
import _ from 'lodash'
import HeaderA from './headerA';
import Avatar from './avatar'
import FollowingModal from './followingModal'
import FollowerModal from './followerModal'
import { connect } from 'react-redux';
import { readUser } from '../actions'

// import axios from 'axios'
// import $ from 'jquery'

class User extends Component{
  constructor(props) {
    super(props)
    this.state = {Component: null}
    this.selectFollowing = this.selectFollowing.bind(this)
    this.selectFollower  = this.selectFollower.bind(this)
    this.handleOnClick   = this.handleOnClick.bind(this)
  }
  componentDidMount () {
    const id = this.props.match.params.id
    const loginUserId = this.props.loginUserId
    this.props.readUser(id,loginUserId)
  }

  componentDidUpdate (prevProps) {
    //profileが更新された際に返されるレスポンスでuserステートを更新(そのため、レスポンスのstatusを指定することで200が返って来るはず)
    //本来、this.props.user[2] != prevProps.user[2]のように異なった場合にupdateするはずだが、常に異なった状態で無限ループに陥るため==に変更(更新の際、一瞬undifinedになるため==だと一度だけ更新してくれる？)
    if(this.props.user[2] == prevProps.user[2]) {
      const id = this.props.match.params.id
      const loginUserId = this.props.loginUserId
      this.props.readUser(id,loginUserId)
    }
  }

  selectFollowing = () => this.setState({Component: FollowingModal})
  selectFollower  = () => this.setState({Component: FollowerModal})
  handleOnClick   = () => this.setState({Component: null})

  
  renderUser() {
    const handleUserAndMovie = (userInfo) => {
      {/* // <!-- ユーザーが存在し且つ動画も存在する場合 --> */}
      if(userInfo[0]){  //動画が存在する場合の条件式
        return(
          <div className="userBox">
            
            <Avatar />

            <div className="boxParts userName">{userInfo[2] ? userInfo[2].name : ""}</div>
        
            {/* <!-- フォロー表示 --> */}
            <div className="followInfoBox">
              <div className="followInfo followingInfo" onClick={this.selectFollowing}>フォロー</div>
              <div className="followInfo followerInfo" onClick={this.selectFollower}>フォロワー</div>
            </div>
            
            <video src={userInfo[0]} height="280px" width="500px" controls></video>
            <div className="boxParts">{userInfo[2] ? userInfo[2].title : ""}</div>
          </div>
        )
      }
    }

    const handleNotMovie = (userInfo) => {
        {/* // <!-- ユーザーは存在するが動画は存在しない場合 --> */}
        if(userInfo[0] === "" && userInfo[2] ? userInfo[2].name != null : "") {
          return(
            <div className="userBox">

              <Avatar />
              
              <div className="boxParts userName">{userInfo[2] ? userInfo[2].name : ""}</div>
          
              {/* <!-- フォロー表示 --> */}
              <div className="followInfoBox">
                <div className="followInfo followingInfo" onClick={this.selectFollowing}>フォロー</div>
                <div className="followInfo followerInfo"  onClick={this.selectFollower}>フォロワー</div>
              </div>
              
            </div>
          )
        }
      }

      const handleNotUserAndMovie = (userInfo) => {
        {/* // <!-- ユーザーが存在しない場合 --> */}
        if(userInfo[2] ? userInfo[2].name == null : "") {
          return(
            <div>
              <div className="noUser">"指定のユーザーは見つかりませんでした"</div>
            </div>
            )
        }
      } 

      const props = this.props
      //{}は必要
      const {Component} = this.state;
      return (
      <React.Fragment>
        {Component && <Component onClick={this.handleOnClick}/>}
        {/* // <!-- ユーザーが存在し且つ動画も存在する場合 --> */}
        {handleUserAndMovie(props.user)}

        {/* // <!-- ユーザーは存在するが動画は存在しない場合 --> */}
        {handleNotMovie(props.user)}
      
        {/* // <!-- ユーザーが存在しない場合 --> */}
        {handleNotUserAndMovie(props.user)}
      </React.Fragment>
       
    )

  }
 
  render() {
    const style = {
      marginTop: "60px",
      backgroundColor:"#e9ecef",
      height: "92vh"
    }
    return (
      <React.Fragment>
        <HeaderA />
        <div style={style}>{this.renderUser()}</div>
      </React.Fragment>
    );
  }
}
const mapStateToProps = state => ({user : state.user,loginUserId : state.auth})
const mapDispatchToProps = ({readUser})
export default connect(mapStateToProps, mapDispatchToProps)(User);


