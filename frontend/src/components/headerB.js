import React from 'react';

const HeaderB = () => {
      return(
        <header>
          <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div className="collapse navbar-collapse" id="navbarsExampleDefault">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                  <a className="nav-link home" href="#" href="@{/index}">ホーム <span className="sr-only">(current)</span></a>
                </li>
              </ul>
            </div>
          </nav>
        </header>
      )
      
}

export default HeaderB;
