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
      if(action.response.good == movie[3].good + 1) {
        //myReviewとレビューの総数の値の変更を反映している
        return { ...movie, [2]:"good",[3]: action.response }
      } else if(action.response.normal == movie[3].normal + 1) {
        return { ...movie, [2]:"normal",[3]: action.response }
      } else if(action.response.bad == movie[3].bad + 1) {
        return { ...movie, [2]:"bad",[3]: action.response }
      } 
    default:
      return movie;
  }
}