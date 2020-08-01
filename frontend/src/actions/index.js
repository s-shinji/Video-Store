import fetch from 'node-fetch'
import axios from 'axios'



export const READ_MOVIE_INDEX = 'READ_MOVIE_INDEX'
export const READ_MOVIE_DETAIL  = 'READ_MOVIE_DETAIL'
export const READ_USER   = 'READ_USER'
export const READ_UPLOAD = 'READ_UPLOAD'
export const SEARCH_MOVIE = 'SEARCH_MOVIE'
export const LOGIN = 'LOGIN'
export const LOGOUT = 'LOGOUT'
// export const LOGOUT = 'LOGOUT'
// export const CREATE_MOVIE = 'CREATE_MOVIE'

const ROOT_URL = 'http://localhost:8080'

export const readMovieIndex = () => async dispatch => {
  const response = await fetch(`${ROOT_URL}/index`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_MOVIE_INDEX, response})
}

export const readMovieDetail = id => async dispatch => {
  const response = await fetch(`${ROOT_URL}/video/${id}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_MOVIE_DETAIL, response})
}

export const readUser = id => async dispatch => {
  const response = await fetch(`${ROOT_URL}/user/${id}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_USER, response})
}

export const postMovie = values => async dispatch =>{
  console.log(values.thumbnail)
  const formData = new FormData()
  formData.append('thumbnail', values.thumbnail)
  formData.append('movie', values.movie)
  formData.append('title', values.title)
  formData.append('loginUserId', values.id)
  console.log(formData)
  await fetch(`${ROOT_URL}/upload`, {mode: 'cors', method: 'POST',body:formData})
  // const response = await fetch(`${ROOT_URL}/upload`, {mode: 'cors', method: 'POST',body:formData})
  // const response = await axios.post(`${ROOT_URL}/upload`,formData)

  // console.log(response)
  // dispatch({type: CREATE_MOVIE, response})
}

export const deleteMovie = ids => async dispatch =>{
  const params = new URLSearchParams()
  params.append('movieId', ids[0])
  params.append('loginUserId', ids[1])
  await fetch(`${ROOT_URL}/delete`, {mode: 'cors',method: 'POST', body:params})
}


// export const postSearch = values => async dispatch => {
//   // 今回はコントローラ側において@RequestParamsではうまくデータが渡らず、@RequestBodyで代替したためredux-formを用いるメリットはあまりなかったかも？
//   const response = await fetch(`${ROOT_URL}/search`, {mode: 'cors', method: 'POST',body: JSON.stringify(values.searchWord)}).then(res => res.json())
//   // body: JSON.stringify(values.searchWord)を使用しない場合は、初期起動時エラーになる？
//   // const response = await fetch(`${ROOT_URL}/search`, {mode: 'cors', method: 'POST'},values.searchWord).then(res => res.json())
//   dispatch({type: SEARCH_MOVIE, response})
// }
export const postSearch = values => async dispatch => {
  const params = new URLSearchParams()
  params.append('searchWord', values.searchWord)
  const response = await fetch(`${ROOT_URL}/search`, {mode: 'cors', method: 'POST',body: params}).then(res => res.json())
  dispatch({type: SEARCH_MOVIE, response})
}

export const postLogin = values => async dispatch => {
  // console.log(values.userName)
  const params = new URLSearchParams()
    params.append('userName', values.userName)
    params.append('password', values.password)
  // console.log(params)
  // 今回はコントローラ側において@RequestParamsではうまくデータが渡らず、@RequestBodyで代替したためredux-formを用いるメリットはあまりなかったかも？
  const response = await fetch(`${ROOT_URL}/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params}).then(res => res.json())
  // const response = await axios.post(`${ROOT_URL}/authenticate`, params,{
  //   withCredentials: true
  // })
  // console.log(response)
  dispatch({type: LOGIN, response})
}

export const postLogout = () => async dispatch =>{
  fetch(`${ROOT_URL}/logout`, {mode: 'cors', method: 'POST'})
  const response = 0;
  dispatch({type: LOGOUT, response})
}

export const createNewRegistration = values => async dispatch =>{
  const params = new URLSearchParams()
  params.append('name', values.name)
  params.append('email', values.email)
  params.append('password', values.password)
  params.append('passwordConfirmation', values.passwordConfirmation)

  fetch(`${ROOT_URL}/Register`, {mode: 'cors', method: 'POST', body:params})
  // dispatch({type: LOGOUT, response})
}
