import _ from 'lodash'
import {
  READ_MOVIE_INDEX,
} from '../actions'

export default (movies = {}, action) => {
  switch(action.type) {
    case READ_MOVIE_INDEX:
      // let arrayAll = []
      // let array = _.mapKeys(action.response[0], "id", 'desc')
      // arrayAll.push(_.orderBy(array, 'id', 'desc'))
      // arrayAll.push(_.mapKeys(action.response[0], "id", 'desc'))
      // arrayAll.push(action.response[1])
      // arrayAll.push({ loginUserId : action.response[2]})
      // arrayAll.push(action.response[2])
      // console.log(arrayAll)
      // return arrayAll;
      return action.response;
    default:
      return movies;
  }
}