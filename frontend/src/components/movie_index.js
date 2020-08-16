import React, { Component } from 'react';
import _ from 'lodash'
import { Link } from 'react-router-dom';
import HeaderA from './headerA';
import { readMovieIndex,deleteMovie } from '../actions' 
import { connect } from 'react-redux'
import { Field, reduxForm} from 'redux-form'


// import Slider from "react-slick";
// import "slick-carousel/slick/slick.css";
// import "slick-carousel/slick/slick-theme.css";
import {SimpleSlider} from './reactSlick'

class MovieIndex extends Component{
  constructor(props) {
    super(props)
    this.onSubmit = this.onSubmit.bind(this)
  }
  
  componentDidMount () {
    this.props.readMovieIndex()
  }
  componentDidUpdate (prevProps) {
    if(this.props!= prevProps) {
      this.props.readMovieIndex()
    }
    // this.props.readMovieIndex()

  }


  async onSubmit(movieId) {
    let arrayDeleteInfo = []
    arrayDeleteInfo.push(movieId)
    arrayDeleteInfo.push(this.props.loginUserId)
    await this.props.deleteMovie(arrayDeleteInfo)
    console.log(this.props)
    await this.props.history.push("/index")

  }
  renderField(field) {
    const {input, type, value} = field
    return(
      <input {...input} type={type} value={value}/>
    )
  }

  renderMovie() {
    const props = this.props
    const loginUserId = props.loginUserId
    const {handleSubmit} = props

    console.log(props)
    
    // const handleTop5Views = _.map(props.movies[1], (value,key) => {
    //   const style = {
    //     marginTop: "60px"
    //   }
  
    //   return(
    //     <React.Fragment key={`top5Views${key}`}>
    //       <li className="indexLoop jsMovie1" style={style}>
    //         <Link to={`/video/${value[0]}`} className="loopLink" >
    //           <img src={value[1]} height="225px" width="400px" />
    //         </Link>
    //       </li>
    //     </React.Fragment>
    //   )

    // })

    const handleMovie = _.map(props.movies[0],(value,key) => {
      const style2={
        display: "block"
      }
      const style3 ={
        paddingTop:"15px",
        paddingBottom:"15px"
      }
      return(
          <React.Fragment key={`movieList${key}`}>
            <div style={style3}>
              <Link to={`/video/${value.id}`} className="movieLink jsMovie2" id={key}>
                <div className="thumbnailBox" id={`imgSrc_${key}`}>
                  <img src={value.image.image} height="150px" width="300px" className="movieIndex" id="hoverChange" />
                </div>
                <div className="movieTitle">{value.title}</div>
              </Link>
      
              <div className="indexInfo">
                <div>
                  <Link to={`/user/${value.userId}`} className="postUserName" style={style3}>
                    <img src={value.user.avatar} className="upAvatar" height="50px" width="50px" />
                    <span id="postUser">投稿者:{value.user.name}</span>
                  </Link>
                </div>
                <div>
                  <label htmlFor = "views">再生回数:</label>
                  <span id="views">{`${value.views}回`}</span>
                </div>
              </div>
              {/* <!-- 現在ログイン中のユーザーと動画投稿者が同じな場合、削除ボタンを表示する --> */}
              {loginUserId == value.userId &&
                <form onSubmit={handleSubmit(() => this.onSubmit(value.id))}>
                  {/* <!-- このname属性が@RequestParamで受け取る際のキーになる（受け取る値はvalue属性） --> */}
                  <input type="hidden" name="movieId" value={value.id} />
                  {/* <Field type="hidden" name="movieId" value={value.id} component={this.renderField}/> */}
                  {/* <input type="hidden" name="loginUserId" value={loginUserId} /> */}
                  {/* <Field type="hidden" name="loginUserId" value={loginUserId} component={this.renderField}/> */}
                  <input type="submit" value="削除" className="deleteBtn" />
                </form>    
              }
            </div>

          </React.Fragment>
        
      )
      
    })

    return (
      <main role="main" className="mainBackground">
        <div id="loopSlide">
          <ul className="jsMovie1">
            {/* {handleTop5Views} */}
            <SimpleSlider value={this.props.movies[1]} />
          </ul>
        </div>
        <div className="images">
          <div className="image">
            {handleMovie}
          </div>
        </div>
      </main>    
    )

  }
 
  render() {
    return (
      <React.Fragment>
        <HeaderA />
        {this.renderMovie()}
      </React.Fragment>
    );
  }
}

//オブジェクト(ハッシュ)を返す場合は戻り値に()が必要??
// const mapStateToProps = state => ({movies : state.movies})
const mapStateToProps = state => ({movies : state.movies,loginUserId : state.auth})

const mapDispatchToProps = ({readMovieIndex,deleteMovie})
export default connect(mapStateToProps, mapDispatchToProps)(reduxForm({form: 'deleteMovieForm'})(MovieIndex));
