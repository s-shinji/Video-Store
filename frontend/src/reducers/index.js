import { combineReducers } from 'redux'
import movies from './movies'
import movie from './movie'
import user from './user'
import search from './search'
import { reducer as form } from 'redux-form'


export default combineReducers({movies, movie, user, search,form})
