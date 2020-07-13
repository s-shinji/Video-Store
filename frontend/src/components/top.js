import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Top extends Component {
    render() {
      return(
        <div className="top">
          <h1 className="topPageH1">Video Store</h1>
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

export default Top;
