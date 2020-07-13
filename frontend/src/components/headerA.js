import React from 'react';
import { Link } from 'react-router-dom';


const HeaderA = () => {
      return(
        <header>
          <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <form role="form" id="logout" action="/logout" method="post" if="!${''.equals(loginUser)}">
              <button type="submit" className="navbar-brand">ログアウト</button>
            </form>   
            {/* <form role="form" id="login" action="/login" method="post" if="${''.equals(loginUser)}">
              <button type="submit" className="navbar-brand">ログイン</button>
            </form> */}
            <Link to="/login" id="login" if="${''.equals(loginUser)}">
              <button type="submit" className="navbar-brand">ログイン</button>
            </Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
        
            <div className="collapse navbar-collapse" id="navbarsExampleDefault">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                  <Link to="/index"className="nav-link">ホーム <span className="sr-only">(current)</span></Link>
                </li>
                <li className="nav-item">
                  <a className="nav-link" href="#" href="@{/upload}">投稿</a>
                </li>
                <li className="nav-item">
                  <a className="nav-link" href="#" href="@{/user/{id}(id=${loginUser})}">マイページ</a>
                </li>
              </ul>
              <form className="form-inline my-2 my-lg-0"  action="@{/search}" method="post">
                <input className="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchWord" />
                <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
              </form>
            </div>
          </nav>
        </header>  
      )
      
}

export default HeaderA;
