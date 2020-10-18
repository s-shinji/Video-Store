import React, { Component } from 'react'
import { connect } from 'react-redux';
import { readUser } from '../actions'
import _ from 'lodash'
import { Link } from 'react-router-dom';

class FollowingModal extends Component {
  constructor(props) {
    super(props)
    this.state= {
      clickCloseModal: false,
    }
    this.click = this.click.bind(this)
  }

  //clickはbindしている
  //onClickで複数の処理を実行するため
  click() {
    this.handleCloseModalToTrue()
    this.props.onClick()
  }
  //アロー関数の場合、bind必要ない？
  handleCloseModalToTrue = () => this.setState({clickCloseModal: true})
  handleCloseModalToFalse = () => this.setState({clickCloseModal: false})

  renderFollowingUser(){
    const props = this.props
    const followingUser = _.map(props.user[5], (value,key) => {
      return(
        <div className="followUserBox" key={`followingUser${key}`}>
          <Link to={`/user/${value.id}`} className="followEach" onClick={this.click}>
            <img src={value.avatar} className="followAvatar" />
            <span className="followName">{value.name}</span>
          </Link>
        </div>
      )
    })
    return( 
      <React.Fragment>
        {followingUser}
      </React.Fragment>
    )
  }
  
  render() {
    if(this.state.clickCloseModal == false) {
      return(
        <div fragment="following" className="modalBackground" id="followingModal">
          <div className="modal">
            {this.renderFollowingUser()}
            <i className="fas fa-times closeModal" onClick={this.click}></i>
          </div>
        </div>
      )
    } else {
      return(
        <React.Fragment>
          {this.handleCloseModalToFalse}
        </React.Fragment>
      ) 
    }
  }
}

const mapStateToProps = state => ({user : state.user})
const mapDispatchToProps = ({readUser})
export default connect(mapStateToProps, mapDispatchToProps)(FollowingModal);

