// import React from 'react';

// const handleAccessMyPage = (props) => {
//   const style = {
//     display:"none"
//   }
//   const loginUserId = props[0]
//   const movieUserId = props[3] ? props[3].id : ""

//   if(movieUserId == loginUserId) {
//     return(
//       //ログインユーザーが自らのユーザーページを訪れた場合
//       <div>
//         <form action={`/user/${loginUserId}`} method="post" encType="multipart/form-data">
//           {/* <div text="${fileError}" style="color:red;"></div> */}

//           <label htmlFor="upAvatar" className="upAvatarBox">
//             <img src="" src={props[3] ? props[3].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
//             <input type="file" id="upAvatar" style={style} name="avatar"  accept="image/*" /> 
//           </label>
//           <input type="submit" value="更新" className="deleteBtn" />
//         </form>
//       </div>
//     )
//   }
// }

// const handleAccessOtherUserPage = (props) => {
//   const loginUserId = props[0]
//   const movieUserId = props[3] ? props[3].id : ""
//   if(movieUserId != loginUserId) {
//     return(
//       // ログインユーザーが他のユーザーページを訪れた場合
//       <div>
//         <label htmlFor="upAvatar" className="upAvatarBox">
//           <img src="" src={props[3] ? props[3].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
//         </label>
//         <div className="followBox">
//           { props[4] == false ? <button className="followBtn"> フォローする </button> : ""}
//           {/* <!-- <a href="#" className="followedBtn"> フォローを外す </a> --> */}
//           { props[4] == true ? <button className="followedBtn"> フォロー中 </button> : "" }
//         </div>
//       </div>
//     )
//   }
// }
// const Avatar = (props) => {
//   return(
//     <React.Fragment>
//       {handleAccessMyPage(props.value)}
//       {handleAccessOtherUserPage(props.value)}
//     </React.Fragment>
//   )
// }

// export default Avatar;


import React, { Component } from 'react';
import { connect } from 'react-redux';


class Avatar extends Component {
  handleAccessMyPage = (props) => {
    const style = {
      display:"none"
    }
    const loginUserId = props[0]
    const movieUserId = props[3] ? props[3].id : ""
  
    if(movieUserId == loginUserId) {
      return(
        //ログインユーザーが自らのユーザーページを訪れた場合
        <div>
          <form action={`/user/${loginUserId}`} method="post" encType="multipart/form-data">
            {/* <div text="${fileError}" style="color:red;"></div> */}
  
            <label htmlFor="upAvatar" className="upAvatarBox">
              <img src="" src={props[3] ? props[3].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
              <input type="file" id="upAvatar" style={style} name="avatar"  accept="image/*" /> 
            </label>
            <input type="submit" value="更新" className="deleteBtn" />
          </form>
        </div>
      )
    }
  }
  
  handleAccessOtherUserPage = (props) => {
    const loginUserId = props[0]
    const movieUserId = props[3] ? props[3].id : ""
    if(movieUserId != loginUserId) {
      return(
        // ログインユーザーが他のユーザーページを訪れた場合
        <div>
          <label htmlFor="upAvatar" className="upAvatarBox">
            <img src="" src={props[3] ? props[3].avatar : ""} alt="" className="upAvatar" height="200px" width="200px" />
          </label>
          <div className="followBox">
            { props[4] == false ? <button className="followBtn"> フォローする </button> : ""}
            {/* <!-- <a href="#" className="followedBtn"> フォローを外す </a> --> */}
            { props[4] == true ? <button className="followedBtn"> フォロー中 </button> : "" }
          </div>
        </div>
      )
    }
  }
  
  render() {
    return(
      <React.Fragment>
        {() => this.handleAccessMyPage(props.value)}
        {() => this.handleAccessOtherUserPage(props.value)}
      </React.Fragment>
    )
    }
}
const mapStateToProps = state => ({user : state.user,loginUserId : state.auth})
export default connect(mapStateToProps,null)(Avatar);