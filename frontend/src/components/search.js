import React, { Component } from 'react';
import _ from 'lodash'
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import HeaderA from './headerA';


class Search extends Component{
  constructor(props) {
    super(props)
    this.state = {
      youtubeLogo: null
    }
    // this.onMouseOver = this.onMouseOver.bind(this)
    // this.onMouseLeave = this.onMouseLeave.bind(this)
  }

  onMouseEnter(key) {
    document.getElementById(`imgSrc2_${key}`).style.opacity = '0.8'
    this.setState({
      youtubeLogo: key
    })
  }

  onMouseLeave(key) {
    document.getElementById(`imgSrc2_${key}`).style.opacity = '1.0'
    this.setState({
      youtubeLogo: null
    })
  }



  renderSearch() {
    const props = this.props

    const style = {
      display:"block"
    } 
    const style2 ={
      paddingTop:"20px",
    }
    const style3 ={
      marginBottom:"30px"
    }

    const handleSearchResult = _.map(props.search[0], (value,key) => {
      return(
        <div key={`searchResult${key}`} style={style3}>
          <Link to={`/video/${value.id}`} className="movieLink" >
            {/* onMouseEnter={this.onMouseEnter(key)}だと無限ループに陥ってしまうため、onMouseEnter={() => this.onMouseEnter(key)}にしている(詳しくはメモに書いている) */}
            <div className="thumbnailBox" id={`imgSrc2_${key}`} onMouseEnter={() => this.onMouseEnter(key)} onMouseLeave={() => this.onMouseLeave(key)}>
              <img src={value.image.image} height="150px" width="300px" className="movieIndex" />
              {this.state.youtubeLogo == key ? <i className='fab fa-youtube'></i> :""}
            </div>
    
            <div text={value.title} className="movieTitle">{value.title}</div>
          </Link>

          <div className="indexInfo">
            <div>
              {/* <!-- 動画投稿者はmovieのインデックス3に入っている --> */}
              <Link  to={`/user/${value.userId}`} className="postUserName"  style={style}>
                <img src="" alt="" src={value.user.avatar} className="upAvatar" height="50px" width="50px" />
                <span id="postUser" text={value.user.name}>投稿者:{value.user.name}</span>
              </Link>
            </div>

            <div>
              <span id="views" text={`${value.views}回`}>再生回数:{value.views}回</span>
            </div>
          </div>
        </div>
      )

    })
    return (
      <React.Fragment>
        {props.search[1] == 0 ? <div className="noMovie" style={style2}>指定の動画は見つかりませんでした</div> : ""}

        <div className="images" style={style2}>
          <div each= "movie, movieStat:${searchResultList}" className="image">
            {handleSearchResult}
          </div>
        </div>

      </React.Fragment>
    )

  }
 
  render() {
    const style = {
      marginTop: "60px",
      minHeight: "95vh"
    }
    return (
      <React.Fragment>
        <HeaderA />
        <div className="mainBackground" style={style}>{this.renderSearch()}</div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = state => ({search : state.search})
export default connect(mapStateToProps,null)(Search);


