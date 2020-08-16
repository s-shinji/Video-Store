import fetch from 'node-fetch'
import axios from 'axios'



export const READ_MOVIE_INDEX = 'READ_MOVIE_INDEX'
export const READ_MOVIE_DETAIL  = 'READ_MOVIE_DETAIL'
export const READ_USER   = 'READ_USER'
export const READ_UPLOAD = 'READ_UPLOAD'
export const POST_PROFILE = 'POST_PROFILE'
export const POST_REVIEW = 'POST_REVIEW'
export const POST_FOLLOW = 'POST_FOLLOW'
export const POST_UNFOLLOW = 'POST_UNFOLLOW'
export const SEARCH_MOVIE = 'SEARCH_MOVIE'
export const LOGIN = 'LOGIN'
export const LOGOUT = 'LOGOUT'
export const CREATE_REGISTRATION = 'CREATE_REGISTRATION'
// export const LOGOUT = 'LOGOUT'
// export const CREATE_MOVIE = 'CREATE_MOVIE'

const ROOT_URL = 'http://localhost:8080'

export const readMovieIndex = () => async dispatch => {
  const response = await fetch(`${ROOT_URL}/index`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_MOVIE_INDEX, response})
}

// export const readMovieDetail = id => async dispatch => {
//   const response = await fetch(`${ROOT_URL}/video/${id}`, {mode: 'cors'}).then(res => res.json())
//   dispatch({type: READ_MOVIE_DETAIL, response})
// }
export const readMovieDetail = (id,loginUserId) => async dispatch => {
  // const params = new URLSearchParams()
  // params.append("loginUserId", loginUserId)
  const response = await fetch(`${ROOT_URL}/video/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_MOVIE_DETAIL, response})
}

export const readUser = id => async dispatch => {
  // const params = new URLSearchParams()
  // params.append("loginUserId", loginUserId)
  // let hashLoginUserId = {}
  // hashLoginUserId.loginUserId= loginUserId
  // console.log(hashLoginUserId)
  const response = await fetch(`${ROOT_URL}/user/${id}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_USER, response})
}
// export const readUser = (id,loginUserId) => async dispatch => {
//   // const params = new URLSearchParams()
//   // params.append("loginUserId", loginUserId)
//   // let hashLoginUserId = {}
//   // hashLoginUserId.loginUserId= loginUserId
//   // console.log(hashLoginUserId)
//   const response = await fetch(`${ROOT_URL}/user/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
//   dispatch({type: READ_USER, response})
// }

export const postMovie = values => async dispatch =>{
  const formData = new FormData()
  formData.append('thumbnail', values.thumbnail)
  formData.append('movie', values.movie)
  formData.append('title', values.title)
  formData.append('loginUserId', values.id)
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

export const postProfile = hashUpProfileInfo => async dispatch => {
  const formData = new FormData()
  formData.append("avatar", hashUpProfileInfo.avatar)
  const response = await fetch(`${ROOT_URL}/user/${hashUpProfileInfo.id}`, {mode: 'cors',method: 'POST', body:formData})
  dispatch({type: POST_PROFILE, response})
}

export const postReview = hashUpReviewInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("review", hashUpReviewInfo.review)
  params.append("loginUserId", hashUpReviewInfo.loginUserId)
  const response = await fetch(`${ROOT_URL}/review/${hashUpReviewInfo.movieId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_REVIEW, response})
}
export const postFollow = hashFollowInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("loginUserId", hashFollowInfo.loginUserId)
  const response = await fetch(`${ROOT_URL}/follow/${hashFollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_FOLLOW, response})
}
export const postUnfollow = hashUnfollowInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("loginUserId", hashUnfollowInfo.loginUserId)
  const response = await fetch(`${ROOT_URL}/followed/${hashUnfollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_UNFOLLOW, response})
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
  // const response = await fetch(`${ROOT_URL}/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params}).then(res => res.json())

  let response = await fetch(`${ROOT_URL}/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params})
  // const response = await axios.post(`${ROOT_URL}/authenticate`, params,{
  //   withCredentials: true
  // })
  if(response.url == "http://localhost:8080/login-error") {
    response = 0;
  } else {
    response = await fetch(`${ROOT_URL}/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params}).then(res => res.json())
  }
  dispatch({type: LOGIN, response})
}

export const postLogout = () => async dispatch =>{
  await fetch(`${ROOT_URL}/logout`, {mode: 'cors', method: 'POST'})
  const response = 0;
  dispatch({type: LOGOUT, response})
}

export const createNewRegistration = values => async dispatch =>{
  const params = new URLSearchParams()
  params.append('name', values.name)
  params.append('email', values.email)
  params.append('password', values.password)
  params.append('passwordConfirmation', values.passwordConfirmation)

  const response = await fetch(`${ROOT_URL}/Register`, {mode: 'cors', method: 'POST', body:params}).then(res => res.json())

  dispatch({type: CREATE_REGISTRATION, response})
}
