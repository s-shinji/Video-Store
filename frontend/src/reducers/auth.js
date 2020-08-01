import {LOGIN, LOGOUT} from '../actions'

export default (login = 0, action) => {
  switch(action.type) {
    case LOGIN:
    case LOGOUT:
      // console.log(action.response)
      return action.response;
    default:
      return login;
  }
}