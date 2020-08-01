import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk'
import reducer from './reducers';

// import { composeWithDevTools } from 'redux-devtools-extension'

import './css/reset.css';
import './css/bootstrap.min.css';
import './css/application.css';
import './css/index.css';
import './css/top.css';
import './css/signin.css';
import './css/upload.css';
import './css/user.css';
import './css/search.css';
import './css/detail.css';
import './css/result.css';



import { BrowserRouter, Route, Switch} from 'react-router-dom'
import Top from './components/top'
import HeaderA from './components/headerA';
import HeaderB from './components/headerB';
import Login from './components/login';
import RegistrationForm from './components/RegistrationForm';
import MovieIndex from './components/movie_index';
import Upload from './components/upload';
import User from './components/user';
import Search from './components/search';
import Detail from './components/detail';
import Auth from './components/auth';
import Result from './components/result';
import * as serviceWorker from './serviceWorker';

const store = createStore(reducer,applyMiddleware(thunk))
ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component= {Top} />
          <Route path="/index" component= {MovieIndex} />
          <Route path="/login" component={Login} />
          <Route path="/RegistrationForm" component={RegistrationForm} />
          <Route path="/user/:id" component={User} />
          <Route path="/search" component={Search} />
          <Route path="/video/:id" component={Detail} />
          <Route path="/result" component={Result} />
          <Auth>
            <Route path="/upload" component={Upload} />
          </Auth>
        </Switch>
      </BrowserRouter>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
