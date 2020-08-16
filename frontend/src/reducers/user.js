import _ from 'lodash'
import {READ_USER, POST_PROFILE, POST_FOLLOW, POST_UNFOLLOW} from '../actions'

export default (user={}, action) => {
  switch(action.type) {
    case POST_PROFILE:
    case READ_USER:
      console.log(action.response)
      return action.response;
    case POST_FOLLOW:
    case POST_UNFOLLOW:
      return {...user, [4]: action.response}
    default:
      return user;
  }
}