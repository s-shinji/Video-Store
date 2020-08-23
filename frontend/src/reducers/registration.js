import {CREATE_REGISTRATION} from '../actions'

export default (registration = 0, action) => {
  switch(action.type) {
    case CREATE_REGISTRATION:
      return action.response;
    default:
      return registration;
  }
}