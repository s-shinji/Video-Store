import fetch from 'node-fetch'
// import axios from 'axios'



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

// const ROOT_URL = 'http://localhost:8080/KdiJ362'
const REACT_APP_BACKEND_URL = '/KdiJ362'
// const REACT_APP_BACKEND_URL = 'https://fierce-forest-67177.herokuapp.com'

export const readMovieIndex = (loginUserId) => async dispatch => {
  console.log("test")
  const response = await fetch(`${REACT_APP_BACKEND_URL}/index?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  // const response = await fetch(`/home?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  console.log(response)
  dispatch({type: READ_MOVIE_INDEX, response})
}

export const readMovieDetail = (id,loginUserId) => async dispatch => {
  const response = await fetch(`${REACT_APP_BACKEND_URL}/video/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  // const response = await fetch(`/video/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_MOVIE_DETAIL, response})
}

export const readUser = (id,loginUserId) => async dispatch => {
  const response = await fetch(`${REACT_APP_BACKEND_URL}/user/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  // const response = await fetch(`/user/${id}?loginUserId=${loginUserId}`, {mode: 'cors'}).then(res => res.json())
  dispatch({type: READ_USER, response})
}

export const postMovie = values => async dispatch =>{
  const formData = new FormData()
  formData.append('thumbnail', values.thumbnail)
  formData.append('movie', values.movie)
  formData.append('title', values.title)
  formData.append('loginUserId', values.id)
  await fetch(`${REACT_APP_BACKEND_URL}/upload`, {mode: 'cors', method: 'POST',body:formData})
  // await fetch(`/upload`, {mode: 'cors', method: 'POST',body:formData})
}

export const deleteMovie = ids => async dispatch =>{
  const params = new URLSearchParams()
  params.append('movieId', ids[0])
  params.append('loginUserId', ids[1])
  await fetch(`${REACT_APP_BACKEND_URL}/delete`, {mode: 'cors',method: 'POST', body:params})
  // await fetch(`/delete`, {mode: 'cors',method: 'POST', body:params})
}

export const postProfile = hashUpProfileInfo => async dispatch => {
  const formData = new FormData()
  formData.append("avatar", hashUpProfileInfo.avatar)
  const response = await fetch(`${REACT_APP_BACKEND_URL}/user/${hashUpProfileInfo.id}`, {mode: 'cors',method: 'POST', body:formData})
  // const response = await fetch(`/user/${hashUpProfileInfo.id}`, {mode: 'cors',method: 'POST', body:formData})
  dispatch({type: POST_PROFILE, response})
}

export const postReview = hashUpReviewInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("review", hashUpReviewInfo.review)
  params.append("loginUserId", hashUpReviewInfo.loginUserId)
  const response = await fetch(`${REACT_APP_BACKEND_URL}/review/${hashUpReviewInfo.movieId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  // const response = await fetch(`/review/${hashUpReviewInfo.movieId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_REVIEW, response})
}
export const postFollow = hashFollowInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("loginUserId", hashFollowInfo.loginUserId)
  const response = await fetch(`${REACT_APP_BACKEND_URL}/follow/${hashFollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  // const response = await fetch(`/follow/${hashFollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_FOLLOW, response})
}
export const postUnfollow = hashUnfollowInfo => async dispatch => {
  const params = new URLSearchParams()
  params.append("loginUserId", hashUnfollowInfo.loginUserId)
  const response = await fetch(`${REACT_APP_BACKEND_URL}/followed/${hashUnfollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  // const response = await fetch(`/followed/${hashUnfollowInfo.userId}`, {mode: 'cors',method: 'POST', body:params}).then(res => res.json())
  dispatch({type: POST_UNFOLLOW, response})
}

export const postSearch = values => async dispatch => {
  const params = new URLSearchParams()
  params.append('searchWord', values.searchWord)
  const response = await fetch(`${REACT_APP_BACKEND_URL}/search`, {mode: 'cors', method: 'POST',body: params}).then(res => res.json())
  // const response = await fetch(`/search`, {mode: 'cors', method: 'POST',body: params}).then(res => res.json())
  dispatch({type: SEARCH_MOVIE, response})
}

export const postLogin = values => async dispatch => {
  const params = new URLSearchParams()
  params.append('userName', values.userName)
  params.append('password', values.password)
  let response = await fetch(`/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params})
  // let response = await fetch(`/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params})
  // if(response.url == "http://localhost:8080/login-error") {
  console.log(response.url)
  if(response.url == "https://fierce-forest-67177.herokuapp.com/login-error") {
    response = 0;
  } else {
    response = await fetch(`/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params}).then(res => res.json())
    // response = await fetch(`/authenticate`, {mode: 'cors', method: 'POST',credentials: 'include',body: params}).then(res => res.json())
  }
  console.log(response)
  dispatch({type: LOGIN, response})
}

export const postLogout = () => async dispatch =>{
  await fetch(`${REACT_APP_BACKEND_URL}/logout`, {mode: 'cors', method: 'POST'})
  // await fetch(`/logout`, {mode: 'cors', method: 'POST'})
  const response = 0;
  dispatch({type: LOGOUT, response})
}

export const createNewRegistration = values => async dispatch =>{
  const params = new URLSearchParams()
  params.append('name', values.name)
  params.append('email', values.email)
  params.append('password', values.password)
  params.append('passwordConfirmation', values.passwordConfirmation)

  const response = await fetch(`${REACT_APP_BACKEND_URL}/Register`, {mode: 'cors', method: 'POST', body:params}).then(res => res.json())
  // const response = await fetch(`/Register`, {mode: 'cors', method: 'POST', body:params}).then(res => res.json())

  dispatch({type: CREATE_REGISTRATION, response})
}
