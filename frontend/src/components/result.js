import React from 'react';
import { Link } from 'react-router-dom';
import HeaderA from './headerA';

const Result = () => {
      return(
        <React.Fragment>
          <HeaderA/>
          <div className="resultBox">
            <p className="success">会員登録が完了しました。</p>
            <Link to="/login" className="goBackLogin">ログイン画面へ</Link>
          </div>
        </React.Fragment>
      )
}

export default Result;
