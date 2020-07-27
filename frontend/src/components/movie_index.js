import React, { Component } from 'react';
import _ from 'lodash'
import { Link } from 'react-router-dom';
import HeaderA from './headerA';
import { readMovieIndex } from '../actions' 
import { connect } from 'react-redux'

// import Slider from "react-slick";
// import "slick-carousel/slick/slick.css";
// import "slick-carousel/slick/slick-theme.css";
import {SimpleSlider} from './reactSlick'

class MovieIndex extends Component{
  
  //componentDidMountに変えて実験中
  componentDidMount () {
    this.props.readMovieIndex()
  }

  // componentDidMount() {
  //   $('#loopSlide ul').simplyScroll({
  //     autoMode: 'loop',
  //     speed: 1,
  //     frameRate: 24,
  //     horizontal: true,
  //     pauseOnHover:   true,
  //     pauseOnTouch: true
  //   });
  // }

  renderMovie() {
    const props = this.props
    
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
              {props.movies[2] == value.id &&
                <form action="/delete" method="POST" >
                  {/* <!-- このname属性が@RequestParamで受け取る際のキーになる（受け取る値はvalue属性） --> */}
                  <input type="hidden" name="movieId" value={value.id} />
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
        {/* <HeaderA value={this.state.}/> */}
        {this.renderMovie()}
      </React.Fragment>
    );
  }
}

//オブジェクト(ハッシュ)を返す場合は戻り値に()が必要??
const mapStateToProps = state => ({movies : state.movies})
const mapDispatchToProps = ({readMovieIndex})
export default connect(mapStateToProps, mapDispatchToProps)(MovieIndex);
