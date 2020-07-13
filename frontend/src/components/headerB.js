import React from 'react';
import { Link } from 'react-router-dom';


const HeaderB = () => {
      return(
        <header>
          <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div className="collapse navbar-collapse" id="navbarsExampleDefault">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                  <Link to="/index" className="nav-link home">ホーム <span className="sr-only">(current)</span></Link>
                </li>
              </ul>
            </div>
          </nav>
        </header>
      )
      
}

export default HeaderB;
