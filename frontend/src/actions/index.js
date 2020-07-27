import fetch from 'node-fetch'


export const READ_MOVIE_INDEX = 'READ_MOVIE_INDEX'
export const READ_MOVIE_DETAIL  = 'READ_MOVIE_DETAIL'
export const READ_USER   = 'READ_USER'
export const READ_UPLOAD = 'READ_UPLOAD'
export const SEARCH_MOVIE = 'SEARCH_MOVIE'
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

// export const readUpload = () => async dispatch => {
//   const response = await fetch(`${ROOT_URL}/upload`, {mode: 'cors'}).then(res => res.json())
//   console.log(response)
//   dispatch({type: READ_UPLOAD, response})
// }

// export const postMovie = values => async dispatch => {
//   const response = await fetch(`${ROOT_URL}/upload`, {mode: 'cors', method: 'POST'}, values).then(res => res.json())
//   console.log(response)
//   dispatch({type: CREATE_MOVIE, response})
// }

export const postSearch = values => async dispatch => {
  // 今回はコントローラ側において@RequestParamsではうまくデータが渡らず、@RequestBodyで代替したためredux-formを用いるメリットはあまりなかったかも？
  const response = await fetch(`${ROOT_URL}/search`, {mode: 'cors', method: 'POST',body: JSON.stringify(values.searchWord)}).then(res => res.json())
  dispatch({type: SEARCH_MOVIE, response})
}