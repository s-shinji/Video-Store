import React from 'react';

const RegistrationForm = () => {
      const style = {
        marginBottom: "10px"
      }
      return(
        <form className="form-signin" action="Register" method="POST">
          <h1 className="h3 mb-3 font-weight-normal">新規登録</h1>
          {/* <div if="${#fields.hasErrors('name')}"  errors="*{name}"  style={style}></div>
          <div if="${#fields.hasErrors('email')}" errors="*{email}" style={style}></div>
          <div errors="*{passwordConfirmation}" style={style}></div> */}
          <label htmlFor="inputName" className="sr-only">ユーザー名</label>
          <input type="text" id="inputName" name="name" className="mb-10 form-control" placeholder="ユーザー名(15字以内)" style={style} required autoFocus />
        
          <label htmlFor="inputEmail" className="sr-only">Email</label>
          <input type="text" id="inputEmail" name="email" className="mb-10 form-control" placeholder="Email" style={style} required autoFocus />
        
        
          <label htmlFor="inputPassword" className="sr-only">パスワード</label>
          {/* <div if="${#fields.hasErrors('password')}" errors="*{password}" style={style}></div> */}
          <input type="password" id="inputPassword"  name="password" className="mb-10 form-control" style={style} placeholder="パスワード" required />
        
          <label htmlFor="inputPasswordConfirmation" className="sr-only">パスワード(確認用)</label>
          <input type="password" id="inputPasswordConfirmation"  name="passwordConfirmation" className="mb-10 form-control" style={style} placeholder="パスワード(確認用)" required />
        
          <div className="checkbox mb-3">
          </div>
          <button className="btn btn-lg btn-success btn-block" type="submit">登録</button>
          <p className="mt-5 mb-3 text-muted">&copy; 2020</p>
        
        </form>
      
      )
      
}

export default RegistrationForm;


