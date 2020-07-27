import _ from 'lodash'
import {
  READ_MOVIE_DETAIL
} from '../actions'

export default (movie = {}, action) => {
  switch(action.type) {
    case READ_MOVIE_DETAIL:
      return action.response;
    default:
      return movie;
  }
}