import React, { Component } from 'react';
import HeaderB from './headerB';
import { Field, reduxForm} from 'redux-form'
import {createNewRegistration} from '../actions'
import { connect } from 'react-redux';
import {maxLength,email} from '../validation'


class RegistrationForm extends Component {
      constructor(props) {
        super(props)
        this.onSubmit = this.onSubmit.bind(this)
      }

      renderField = (field) => {
        const { input, type, id, className, placeholder, style , meta: {touched, error}} = field
        const errorStyle = {
          color:"red"
        }    
        return(
          <React.Fragment>
            {touched && error && <p style={errorStyle}>{error}</p>}
            <input type={type}  {...input} id={id} className={className} placeholder={placeholder} style={style} />
          </React.Fragment>
        )
      }

      async onSubmit(values) {
        await this.props.createNewRegistration(values)
        if(this.props.registration == 0) {
        await this.props.history.push('/result')
        }

      }
    
      
      render() {
        const style = {
          marginBottom: "10px"
        }
        const style2 = {
          color:"red"
        }
        const {handleSubmit,pristine, submitting, invalid} = this.props
  
        return(
          <React.Fragment>
            <HeaderB />
            <form className="form-signin" action="Register" method="POST" onSubmit={handleSubmit(this.onSubmit)}>
              <h1 className="h3 mb-3 font-weight-normal">新規登録</h1>
              {this.props.registration == 1 ? 
              <React.Fragment>
                <p style={style2}>すでに登録済みのユーザー名です</p>
                <p style={style2}>すでに登録済みのメールアドレスです</p>
              </React.Fragment>
              : ""}
              {this.props.registration == 2 ? 
                <p style={style2}>すでに登録済みのユーザー名です</p>
              : ""}
              {this.props.registration == 3 ? 
                <p style={style2}>すでに登録済みのメールアドレスです</p>
              : ""}
              {this.props.registration == 4 ? 
                <p style={style2}>パスワードが一致していません</p>
              : ""}
              {/* <div if="${#fields.hasErrors('name')}"  errors="*{name}"  style={style}></div>
              <div if="${#fields.hasErrors('email')}" errors="*{email}" style={style}></div>
              <div errors="*{passwordConfirmation}" style={style}></div> */}
              <label htmlFor="inputName" className="sr-only">ユーザー名</label>
              {/* <input type="text" id="inputName" name="name" className="mb-10 form-control" placeholder="ユーザー名(15字以内)" style={style} required autoFocus /> */}
              <Field type="text" id="inputName" name="name" className="mb-10 form-control" placeholder="ユーザー名(15字以内)"
                style={style} component={this.renderField} />
            
              <label htmlFor="inputEmail" className="sr-only">Email</label>
              {/* <input type="text" id="inputEmail" name="email" className="mb-10 form-control" placeholder="Email" style={style} required autoFocus /> */}
              <Field type="text" id="inputEmail" name="email" className="mb-10 form-control" placeholder="Email" 
                style={style} component={this.renderField} />
            
            
              <label htmlFor="inputPassword" className="sr-only">パスワード</label>
              {/* <div if="${#fields.hasErrors('password')}" errors="*{password}" style={style}></div> */}
              {/* <input type="password" id="inputPassword"  name="password" className="mb-10 form-control" style={style} placeholder="パスワード" required /> */}
              <Field type="password" id="inputPassword"  name="password" className="mb-10 form-control" 
                style={style} placeholder="パスワード" component={this.renderField} />
            
              <label htmlFor="inputPasswordConfirmation" className="sr-only">パスワード(確認用)</label>
              {/* <input type="password" id="inputPasswordConfirmation"  name="passwordConfirmation" className="mb-10 form-control" style={style} placeholder="パスワード(確認用)" required /> */}
              <Field type="password" id="inputPasswordConfirmation"  name="passwordConfirmation" className="mb-10 form-control" 
                style={style} placeholder="パスワード(確認用)" component={this.renderField}/>
            
              <div className="checkbox mb-3">
              </div>
              <button className="btn btn-lg btn-success btn-block" type="submit" disabled={pristine || submitting || invalid }>登録</button>
              <p className="mt-5 mb-3 text-muted">&copy; 2020</p>
            
            </form>
          </React.Fragment>
        )
      }
      
}
const validate = values => {
  const errors = {}

  if(!values.name) errors.name = "ユーザー名を入力してください" 
  if(values.name) errors.name =  maxLength(values.name, 15, '15文字以下で入力してください')
  if(!values.email) errors.email = "メールアドレスを入力してください" 
  if(values.email) errors.email = email(values.email, 'メールアドレスが不正です') 
  if(!values.password) errors.password = "パスワードを入力してください" 
  if(!values.passwordConfirmation) errors.passwordConfirmation = "再度パスワードを入力してください" 

  return errors
}
const mapStateToProps = state => ({registration : state.registration})
const mapDispatchToProps = ({createNewRegistration})
export default connect(mapStateToProps, mapDispatchToProps)(reduxForm({validate,form: 'registrationForm'})(RegistrationForm));


