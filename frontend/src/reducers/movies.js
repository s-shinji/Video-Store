import _ from 'lodash'
import {
  READ_MOVIE_INDEX,
} from '../actions'

export default (movies = {}, action) => {
  switch(action.type) {
    case READ_MOVIE_INDEX:
      return action.response;
    default:
      return movies;
  }
}