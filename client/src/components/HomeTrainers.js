import React from "react";
import {Col, Container, Row} from "react-bootstrap";
import trainer1 from "../assets/img/trainer1.jpg";
import trainer2 from "../assets/img/trainer2.jpg";
import trainer3 from "../assets/img/trainer3.jpg";
import trainer4 from "../assets/img/trainer4.jpg";

class HomeTrainers extends React.Component {
  render() {
    return(
      <div className="trainer-container">
        <Container >
          <Row>
            <Col md={{ span: 8, offset: 2}} className="text-center">
              <h2>Our Experienced Trainers</h2>
            </Col>
          </Row>
          <Row className="trainer-row">
            <Col md={3} sm={3} className="text-center">
              <div className="trainer-entry">
                <img
                  className="trainer-img"
                  src={trainer1}
                  alt="Trainer 1"
                />
                <div className="trainer-desc">
                  <h3>Trainer Name</h3>
                  <p>XXXXXX Class Trainer.</p>
                </div>
              </div>
            </Col>
            <Col md={3} sm={3} className="text-center">
              <div className="trainer-entry">
                <img
                  className="trainer-img"
                  src={trainer2}
                  alt="Trainer 2"
                />
                <div className="trainer-desc">
                  <h3>Trainer Name</h3>
                  <p>XXXXXX Class Trainer.</p>
                </div>
              </div>
            </Col>
            <Col md={3} sm={3} className="text-center">
              <div className="trainer-entry">
                <img
                  className="trainer-img"
                  src={trainer3}
                  alt="Trainer 3"
                />
                <div className="trainer-desc">
                  <h3>Trainer Name</h3>
                  <p>XXXXXX Class Trainer.</p>
                </div>
              </div>
            </Col>
            <Col md={3} sm={3} className="text-center">
              <div className="trainer-entry">
                <img
                  className="trainer-img"
                  src={trainer4}
                  alt="Trainer 4"
                />
                <div className="trainer-desc">
                  <h3>Trainer Name</h3>
                  <p>XXXXXX Class Trainer.</p>
                </div>
              </div>
            </Col>
          </Row>
        </Container>
      </div>

    );
  }
}

export default HomeTrainers;
