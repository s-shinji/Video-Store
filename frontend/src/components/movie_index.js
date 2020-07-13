import React, { Component } from 'react';
import fetch from 'node-fetch'
import _ from 'lodash'
// import $ from 'jquery'

class MovieIndex extends Component{
  constructor(props) {
    super(props)
    this.state = {
      movieList: [],
      top5Views: []
    }
  }

  componentWillMount () {
    const URL = 'http://localhost:8080/index'
    fetch(URL, {mode: 'cors'})
    .then(res => 
      res.json())
    .then(data => {
      this.setState({
        movieList: data[0],
        top5Views: data[1]
      })

    });
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
    const top5Views = _.map(this.state.top5Views, (value,key) => {
      console.log(value)
      return(
        <React.Fragment key={`top5Views${key}`}>
          <li className="indexLoop jsMovie1">
            <a className="loopLink">
              <img src={value[1]} height="225px" width="400px" />
            </a>
          </li>
        </React.Fragment>
      )

    })

    const movie = _.map(this.state.movieList,(value,key) => {
      const style={
        display: "block"
      }
      return(
          
          <div key={`movieList${key}`}>
            <a className="movieLink jsMovie2" id={key}>
              <div className="thumbnailBox" id={`imgSrc_${key}`}>
                <img src={value.image.image} height="150px" width="300px" className="movieIndex" id="hoverChange" />
              </div>
              <div text={value.title} className="movieTitle">{value.title}</div>
            </a>
    
            <div className="indexInfo">
              <div>
                <a  className="postUserName" style={style}>
                  <img src="" alt="" src={value.user.avatar} className="upAvatar" height="50px" width="50px" />
                  <span id="postUser" text={value.user.name}>投稿者:{value.user.name}</span>
                </a>
              </div>
              <div>
                <label htmlFor = "views">再生回数:</label>
                <span id="views" text={`${value.views}回`}>{`${value.views}回`}</span>
              </div>
            </div>
          </div>
      )
      
    })

    return (
      <main role="main" className="mainBackground">
        <div id="loopSlide">
          <ul className="jsMovie1">
            {top5Views}
          </ul>
        </div>
        <div className="images">
          <div className="image">
            {movie}
          </div>
        </div>
      </main>    
    )

  }
 
  render() {
    return (
      <React.Fragment>{this.renderMovie()}</React.Fragment>
    );
  }
}

export default MovieIndex;


