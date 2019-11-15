import React from "react";
import {Col, Row} from "react-bootstrap";
import {Button} from "antd";
import trainer1 from "../assets/img/trainer1.jpg";
import {Link} from "react-router-dom"

class AvailableTrainers extends React.Component {

  render() {
    return(
      <Row>
        <Col xs={6} sm={4} md={3} className="trainerInfo">
          <img
            className="trainerImg"
            src={trainer1}
            alt="Trainer 1"
          />
          <h4 className="trainerName">Trainer's Name</h4>
          <p className="trainerState">Trainer's Statement</p>
          <p><Link to="/chat" className="chatBtn btn-learn">Chat</Link></p>
        </Col>
        <Col xs={6} sm={4} md={3} className="trainerInfo">
          <img
            className="trainerImg"
            src={trainer1}
            alt="Trainer 1"
          />
          <h4 className="trainerName">Trainer's Name</h4>
          <p className="trainerState">Trainer's Statement</p>
          <p><Link to="/chat" className="chatBtn btn-learn">Chat</Link></p>
        </Col>
        <Col xs={6} sm={4} md={3} className="trainerInfo">
          <img
            className="trainerImg"
            src={trainer1}
            alt="Trainer 1"
          />
          <h4 className="trainerName">Trainer's Name</h4>
          <p className="trainerState">Trainer's Statement</p>
          <p className="btnPad"><Link to="/chat" className="chatBtn btn-learn">Chat</Link></p>
        </Col>
        <Col xs={6} sm={4} md={3} className="trainerInfo">
          <img
            className="trainerImg"
            src={trainer1}
            alt="Trainer 1"
          />
          <h4 className="trainerName">Trainer's Name</h4>
          <p className="trainerState">Trainer's Statement</p>
          <p><Link to="/chat" className="chatBtn btn-primary btn-learn">Chat</Link></p>
        </Col>
      </Row>
    );
  }
}

export default AvailableTrainers;
