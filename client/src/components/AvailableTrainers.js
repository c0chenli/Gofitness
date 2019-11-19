import React from "react";
import {Col, Row} from "react-bootstrap";
import {Button} from "antd";
import trainer1 from "../assets/img/trainer1.jpg";
import {Link} from "react-router-dom"
import {API_ROOT} from "../constants"

class AvailableTrainers extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      trainers: [],
    };
  }

  componentDidMount() {
    fetch(`${API_ROOT}trainee/getAllTrainer`, {
      method: 'GET',
      headers: {'Content-Type':'application/json; charset=utf-8; Access-Control-Allow-Origin: *'},
    }).then(res => res.json()).then(
      data => {
        this.setState({trainers: data});
      }
    ).catch((status) => {
      window.alert(status);
    });
  }

  render() {
    const {trainers} = this.state;
    return(
      <Row id="trainer-block">
        {trainers.map(({firstname, lastname, categories}) =>
          <Col xs={6} sm={4} md={3} className="trainerInfo">
            <img
              className="trainerImg"
              src={trainer1}
              alt="Trainer 1"
            />
            <h4 className="trainerName">{firstname} {lastname}</h4>
            {categories.map(category =>
              <p className="trainerState">{category}</p>
            )}
            <div><Link to="/chat" className="chatBtn btn-learn">Chat</Link></div>
          </Col>
        )}
      </Row>
    );
  }
}

export default AvailableTrainers;
