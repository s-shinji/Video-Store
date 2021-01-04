import React, { Component } from 'react'
import { connect } from 'react-redux';
import { readUser } from '../actions'
import _ from 'lodash'
import { Link } from 'react-router-dom';

class FollowerModal extends Component {
  constructor(props) {
    super(props)
    this.state= {
      clickCloseModal: false,
    }
    this.handleCloseModalToFalse = this.handleCloseModalToFalse.bind(this)
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

  renderFollowerUser(){
    const props = this.props
    const followerUser = _.map(props.user[6], (value,key) => {
      return(
        <div className="followUserBox" key={`followerUser${key}`}>
          <Link to={`/user/${value.id}`} className="followEach" onClick={this.click}>
            <img src={value.avatar} className="followAvatar" />
            <span className="followName">{value.name}</span>
          </Link>
        </div>
      )
    })
    return( 
      <React.Fragment>
        {followerUser}
      </React.Fragment>
    )

  }

  render() {
    if(this.state.clickCloseModal == false){
      return(
        <div className="modalBackground" id="followerModal">
          <div className="modal">
          {this.renderFollowerUser()}
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
export default connect(mapStateToProps, mapDispatchToProps)(FollowerModal);

