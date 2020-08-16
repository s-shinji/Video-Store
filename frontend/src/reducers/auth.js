import {LOGIN, LOGOUT} from '../actions'

export default (login = 0, action) => {
  switch(action.type) {
    case LOGIN:
    case LOGOUT:
      return action.response;
    default:
      return login;
  }
}