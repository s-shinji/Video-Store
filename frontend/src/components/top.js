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
              <a href="#" className="topList">ログイン</a>
              <a href="#" className="topList">新規登録</a>
            </li>
          </ul>
        </div>     
      )
    }
}

export default Top;
