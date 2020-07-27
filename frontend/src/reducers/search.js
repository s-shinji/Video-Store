import {SEARCH_MOVIE} from '../actions'

export default (searchMovie = {}, action) => {
  switch(action.type) {
    case SEARCH_MOVIE:
      return action.response;
    default:
      return searchMovie;
  }
}