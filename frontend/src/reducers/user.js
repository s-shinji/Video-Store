import _ from 'lodash'
import {READ_USER, POST_PROFILE, POST_FOLLOW, POST_UNFOLLOW} from '../actions'

export default (user={}, action) => {
  switch(action.type) {
    case POST_PROFILE:
    case READ_USER:
      return action.response;
    case POST_FOLLOW:
    case POST_UNFOLLOW:
      return {...user, [3]: action.response}
    default:
      return user;
  }
}