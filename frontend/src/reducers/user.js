import _ from 'lodash'
import {READ_USER} from '../actions'

export default (user={}, action) => {
  switch(action.type) {
    case READ_USER:
      return action.response;
    default:
      return user;
  }
}