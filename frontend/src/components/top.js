import React from 'react';
import { Component } from 'react';
import { Link } from 'react-router-dom';
import { postLogin } from '../actions';
import { connect } from 'react-redux';



class Top extends Component {
  constructor(props) {
    super(props)
    this.onSubmit = this.onSubmit.bind(this)
  }
  async onSubmit(e) {
    e.preventDefault()
    let values = {}
    values.userName = "ゲストユーザ"
    values.password = "guest"
    await this.props.postLogin(values)
    await this.props.history.push('/index')
  }
  render() {
    return(
      <div className="top">
        <h1 className="topPageH1">Video Store</h1>
        <form className="topPageH1" onSubmit={this.onSubmit}>
          <button className="btn btn-lg btn-success btn-block" type="submit">ゲストユーザでログイン</button>
        </form>
        <ul className="topPageUl">
          <li className="upper">
            <Link to="/index" className="topList">一覧ページ</Link>
          </li>
          <li className="lower">
            <Link to="/login" className="topList">ログイン</Link>
            <Link to="/RegistrationForm" className="topList">新規登録</Link>
          </li>
        </ul>
      </div>     
    )
  }
}

const mapDispatchToProps = ({postLogin})
export default connect(null,mapDispatchToProps)(Top);
