import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import HeaderB from './headerB';
import { connect } from 'react-redux';
import { Field, reduxForm} from 'redux-form'

import { postLogin } from '../actions'

class Login extends Component{
  constructor(props) {
    super(props)
    this.onSubmit = this.onSubmit.bind(this)
  }
  async onSubmit(values) {
    console.log(values)
    await this.props.postLogin(values)
    this.props.history.push('/index')
  }
  renderField(field) {
    const { input, type, id, className, placeholder, style ,required} = field
    return(
      // {...input}はname属性を展開している？
      <input type={type}  {...input} id={id} className={className} placeholder={placeholder} style={style} required={required}/>
    )

  }
  
  render() {
      const {handleSubmit} = this.props
      const style = {
        marginBottom: "10px"
      }
      return(
        <React.Fragment>
          <HeaderB />
          <form className="form-signin" onSubmit={handleSubmit(this.onSubmit)}>
            <h1 className="h3 mb-3 font-weight-normal">ログインしてください</h1>
            <label htmlFor="inputName" className="sr-only">ユーザー名</label>
            {/* <input type="text" id="inputName user" name="userName" className="form-control" placeholder="ユーザー名" style={style} required autoFocus /> */}
            <Field type="text" id="inputName user" name="userName" className="form-control" placeholder="ユーザー名" style={style} required autoFocus component={this.renderField}/>

            <label htmlFor="inputPassword" className="sr-only">パスワード</label>
            {/* <input type="password" id="inputPassword password"  name="password" className="form-control" placeholder="パスワード" style={style} required /> */}
            <Field type="password" id="inputPassword password"  name="password" className="form-control" placeholder="パスワード" style={style} required component={this.renderField}/>
            <div className="checkbox mb-3">
              <label>
                {/* <input type="checkbox" value="remember-me" /> 保存する */}
                {/* <Field type="checkbox" value="remember-me" component={this.renderField}/> 保存する */}
              </label>
            </div>
            <button className="btn btn-lg btn-success btn-block" type="submit">ログイン</button>
            <Link to="/RegistrationForm"className="btn btn-lg btn-success btn-block" type="submit">新規登録</Link>
            <p className="mt-5 mb-3 text-muted">&copy; 2020</p>

          </form>
        </React.Fragment>

      )
  }
      
}
const mapDispatchToProps = ({postLogin})
export default connect(null,mapDispatchToProps)(reduxForm({ form: 'loginForm'})(Login));


