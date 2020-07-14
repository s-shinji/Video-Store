import React from 'react';
import ReactDOM from 'react-dom';
import './css/reset.css';
import './css/bootstrap.min.css';
import './css/application.css';
import './css/index.css';
import './css/top.css';
import './css/signin.css';
import './css/upload.css';


import { BrowserRouter, Route, Switch} from 'react-router-dom'
import Top from './components/top'
import HeaderA from './components/headerA';
import HeaderB from './components/headerB';
import Login from './components/login';
import RegistrationForm from './components/RegistrationForm';
import MovieIndex from './components/movie_index';
import Upload from './components/upload';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      {/* <Switch> */}
      <Route exact path="/" component= {Top} />
      <Route path="/index" component= {HeaderA, MovieIndex} />
      <Route path="/(index|upload|user|search|video)/" component={HeaderA} />
      <Route path="/(login|RegistrationForm)/" component={HeaderB} />
      <Route path="/login" component={Login} />
      <Route path="/RegistrationForm" component={RegistrationForm} />
      <Route path="/upload" component={Upload} />
      {/* </Switch> */}
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
