import React, {Component} from 'react';
import { Link } from 'react-router-dom';
import {connect} from 'react-redux';
import {postSearch} from '../actions'
import { Field, reduxForm} from 'redux-form'




class HeaderA extends Component {

  constructor(props) {
    super(props)
    this.onSubmit = this.onSubmit.bind(this)
  }

  async onSubmit(values) {
  // e.preventDefault();
  // const values = document.getElementById("searchForm").value
  // console.log(values)
  
  // {searchWord: "bell"}の形で飛んでいく
  await this.props.postSearch(values)
  this.props.history.push('/search')
  }

  renderField(field) {
    const { input, type} = field
    return(
    <input className="form-control mr-sm-2" type={type} placeholder="Search" {...input} />
    )
  }


  render() {
    const {handleSubmit} = this.props

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
                <Link to="/upload" className="nav-link">投稿</Link>
              </li>
              <li className="nav-item">
                {/* LinkのURLを/user/{this.props.id}などに変更する */}
                {/* <Link to={`/user/${}`} className="nav-link">マイページ</Link> */}
              </li>
            </ul>
            <form className="form-inline my-2 my-lg-0"  onSubmit={handleSubmit(this.onSubmit)}>
              {/* <input className="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchWord" id="searchForm"/> */}
              <Field name="searchWord" id="searchForm" type="text" component={this.renderField} />
              <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
          </div>
        </nav>
      </header>  
    )
  }
      
}

const mapDispatchToProps = ({postSearch})
export default connect(null, mapDispatchToProps)(reduxForm({ form: 'searchForm'})(HeaderA));
