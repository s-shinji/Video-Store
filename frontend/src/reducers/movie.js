import _ from 'lodash'
import {
  READ_MOVIE_DETAIL,
  POST_REVIEW

} from '../actions'

export default (movie = {}, action) => {
  switch(action.type) {
    case READ_MOVIE_DETAIL:
      return action.response;
    case POST_REVIEW:
      if(action.response.good == 1) {
        return { ...movie, [2]:"good",[3]: action.response }
      } else if(action.response.normal == 1) {
        return { ...movie, [2]:"normal",[3]: action.response }
      } else if(action.response.bad == 1) {
        return { ...movie, [2]:"bad",[3]: action.response }
      } 
    default:
      return movie;
  }
}