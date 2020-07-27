import React from 'react';
import { Link } from 'react-router-dom';
import HeaderB from './headerB';


const Login = () => {
      const style = {
        marginBottom: "10px"
      }
      return(
        <React.Fragment>
          <HeaderB />
          <form className="form-signin" action="/authenticate" method="POST">
            <h1 className="h3 mb-3 font-weight-normal">ログインしてください</h1>
            <label htmlFor="inputName" className="sr-only">ユーザー名</label>
            <input type="text" id="inputName user" name="userName" className="form-control" placeholder="ユーザー名" style={style} required autoFocus />

            <label htmlFor="inputPassword" className="sr-only">パスワード</label>
            <input type="password" id="inputPassword password"  name="password" className="form-control" placeholder="パスワード" style={style} required />
            <div className="checkbox mb-3">
              <label>
                <input type="checkbox" value="remember-me" /> 保存する
              </label>
            </div>
            <button className="btn btn-lg btn-success btn-block" type="submit">ログイン</button>
            <Link to="/RegistrationForm"className="btn btn-lg btn-success btn-block" type="submit">新規登録</Link>
            <p className="mt-5 mb-3 text-muted">&copy; 2020</p>

          </form>
        </React.Fragment>

      )
      
}

export default Login;


