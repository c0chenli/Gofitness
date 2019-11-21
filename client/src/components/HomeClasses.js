import React from "react";
import {Col, Container, Row} from "react-bootstrap";
import '../styles/HomeClasses.css';

class HomeClasses extends React.Component {

  render() {
    return(
      <Container className="container">
        <Row>
          <Col md={{ span: 8, offset: 2}} className="text-center">
            <h2>Being fit is attractive</h2>
          </Col>
        </Row>
        <Row>
          <Col md={3} className="text-center">
            <div className="classes">
              <span className="icon">
                <i className="flaticon-gym"></i>
              </span>
              <div className="classdes">
                <h3>Cardio Class</h3>
                <p>Class description goes here.</p>
              </div>
            </div>
          </Col>
          <Col md={3} className="text-center">
            <div className="classes">
              <span className="icon">
                <i className="flaticon-gloves"></i>
              </span>
              <div className="classdes">
                <h3>Boxing Class</h3>
                <p>Class description goes here.</p>
              </div>
            </div>
          </Col>
          <Col md={3} className="text-center">
            <div className="classes">
              <span className="icon">
                <i className="flaticon-weightlifting"></i>
              </span>
              <div className="classdes">
                <h3>Body Building Class</h3>
                <p>Class description goes here.</p>
              </div>
            </div>
          </Col>
          <Col md={3} className="text-center">
            <div className="classes">
              <span className="icon">
                <i className="flaticon-meditation"></i>
              </span>
              <div className="classdes">
                <h3>Yoga Class</h3>
                <p>Class description goes here.</p>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default HomeClasses;
