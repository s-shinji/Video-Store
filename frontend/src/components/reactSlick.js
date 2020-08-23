import React from 'react';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import _ from 'lodash'
import { Link } from 'react-router-dom';

const handleTop5Views = (top5Views) => {
  
  return(
    _.map(top5Views.value, (value,key) => {
      const style = {
        marginTop: "60px"
        
      }
      const style2= {
        fontSize:"50px",
        fontWeight:"bold",
        marginLeft:"50px"
      }
      const style3= {
        marginLeft: "250px"
      }

      return(
        <React.Fragment key={`top5Views${key}`}>
          <li style={style}>
            <span style={style2}>{`NO.${key + 1}`}</span>
            <Link to={`/video/${value[0]}`} className="loopLink">
              <img src={value[1]} height="225px" width="600px" style={style3}/>
            </Link>
          </li>
        </React.Fragment>
      )

    })
  )
}

export const SimpleSlider = (top5Views) => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToScroll: 1
  };

  return (
    <Slider {...settings}>
      {handleTop5Views(top5Views)}
    </Slider>
  )
}

