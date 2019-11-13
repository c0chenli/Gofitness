import React from 'react';
import bg1 from '../assets/img/bg1.jpg';
import bg2 from '../assets/img/bg2.jpg';
import bg3 from '../assets/img/bg3.jpg';
import Carousel from 'react-bootstrap/Carousel';

class HomeSlider extends React.Component {

  render() {
    return(
      <Carousel id="home-slider" fade='true'>
        <Carousel.Item>
          <img
            className="sliderpic"
            src={bg1}
            alt="First slide"
          />
          <Carousel.Caption>
            <p><a href="../SignIn.js" className="btn btn-primary btn-lg btn-learn">Login / Sign up</a></p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="sliderpic"
            src={bg2}
            alt="Second slide"
          />

          <Carousel.Caption>
            <p><a href="#" className="btn btn-primary btn-lg btn-learn">Login / Sign up</a></p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="sliderpic"
            src={bg3}
            alt="Third slide"
          />

          <Carousel.Caption>
            <p><a href="#" className="btn btn-primary btn-lg btn-learn">Login / Sign up</a></p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>

    );

  }
}



export default HomeSlider;
