import React, { Component } from 'react';
import HeaderB from './headerB';
import { Field, reduxForm} from 'redux-form'
import {createNewRegistration} from '../actions'
import { connect } from 'react-redux';


class RegistrationForm extends Component {
      constructor(props) {
        super(props)
        this.onSubmit = this.onSubmit.bind(this)
      }

      renderField = (field) => {
        const { input, type, id, className, placeholder, style ,required} = field
        return(
          <input type={type}  {...input} id={id} className={className} placeholder={placeholder} style={style} required={required}/>
        )
      }

      async onSubmit(values) {
        await this.props.createNewRegistration(values)
        await this.props.history.push('/result')
      }
    
      
      render() {
        const style = {
          marginBottom: "10px"
        }
        const {handleSubmit} = this.props
  
        return(
          <React.Fragment>
            <HeaderB />
            <form className="form-signin" action="Register" method="POST" onSubmit={handleSubmit(this.onSubmit)}>
              <h1 className="h3 mb-3 font-weight-normal">新規登録</h1>
              {/* <div if="${#fields.hasErrors('name')}"  errors="*{name}"  style={style}></div>
              <div if="${#fields.hasErrors('email')}" errors="*{email}" style={style}></div>
              <div errors="*{passwordConfirmation}" style={style}></div> */}
              <label htmlFor="inputName" className="sr-only">ユーザー名</label>
              {/* <input type="text" id="inputName" name="name" className="mb-10 form-control" placeholder="ユーザー名(15字以内)" style={style} required autoFocus /> */}
              <Field type="text" id="inputName" name="name" className="mb-10 form-control" placeholder="ユーザー名(15字以内)" style={style} required autoFocus component={this.renderField}/>
            
              <label htmlFor="inputEmail" className="sr-only">Email</label>
              {/* <input type="text" id="inputEmail" name="email" className="mb-10 form-control" placeholder="Email" style={style} required autoFocus /> */}
              <Field type="text" id="inputEmail" name="email" className="mb-10 form-control" placeholder="Email" style={style} required autoFocus component={this.renderField}/>
            
            
              <label htmlFor="inputPassword" className="sr-only">パスワード</label>
              {/* <div if="${#fields.hasErrors('password')}" errors="*{password}" style={style}></div> */}
              {/* <input type="password" id="inputPassword"  name="password" className="mb-10 form-control" style={style} placeholder="パスワード" required /> */}
              <Field type="password" id="inputPassword"  name="password" className="mb-10 form-control" style={style} placeholder="パスワード" required component={this.renderField}/>
            
              <label htmlFor="inputPasswordConfirmation" className="sr-only">パスワード(確認用)</label>
              {/* <input type="password" id="inputPasswordConfirmation"  name="passwordConfirmation" className="mb-10 form-control" style={style} placeholder="パスワード(確認用)" required /> */}
              <Field type="password" id="inputPasswordConfirmation"  name="passwordConfirmation" className="mb-10 form-control" style={style} placeholder="パスワード(確認用)" required component={this.renderField}/>
            
              <div className="checkbox mb-3">
              </div>
              <button className="btn btn-lg btn-success btn-block" type="submit">登録</button>
              <p className="mt-5 mb-3 text-muted">&copy; 2020</p>
            
            </form>
          </React.Fragment>
        )
      }
      
}
const mapDispatchToProps = ({createNewRegistration})
export default connect(null, mapDispatchToProps)(reduxForm({form: 'registrationForm'})(RegistrationForm));


