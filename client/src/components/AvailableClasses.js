import React from "react";
import {Col, Row} from "react-bootstrap";
import bg1 from "../assets/img/bg1.jpg";
import yoga from '../assets/img/yoga.png'
import bg3 from '../assets/img/bg3.jpg'
import bg2 from "../assets/img/bg2.jpg";
import crossfit1 from '../assets/img/crossfit1.png'
import weightloss from '../assets/img/weightloss.png'
import {Link} from "react-router-dom"
import '../styles/AvailableClasses.css'
class AvailableClasses extends React.Component {

  render() {
    return(
      <div className = "bg">
        <Row>
          <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={bg1}
              alt="boxing"
            />
            <h4 className="ClassName">Boxing</h4>
            <p className="ClassAbout">
                Interested in giving Boxing a shot, but maybe feel a little unsure of yourself!?
              This class teaches you your basic boxing stance, basic footwork, and how to throw 
              a punch while also gradually building up your stamina, strength, and endurance!
              </p>
          </Col>
          <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={yoga}
              alt="yoga"
            />
            <h4 className="className">Yoga</h4>
            <p className="ClassAbout">
            A yoga class for those newer to yoga or those wanting a basic flow class.  
            This class introduces foundational yoga postures, teaches you how to breath
             and helps you feel more comfortable in the yoga practice.
            </p>
          </Col>
          <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={bg2}
              alt="weightlifting"
            />
            <h4 className="className">Weightlifting</h4>
            <p className="ClassAbout">In this course, participants will gain
             practical information from top-level coaches about weightlifting technique,
              assessing movement, motor learning, biomechanics, effective coaching, and
               programming training. </p>
          </Col>
          <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={bg3}
              alt="Trainer 1"
            />
            <h4 className="className">Fitness</h4>
            <p className="ClassAbout">This class will focus on building and maintaining 
            athletic abilities. Through a combination of a variety of training such as
             strength, endurance, flexibility as well as others to aid participants 
             become well rounded in all physical activities</p>
          </Col>
          <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={crossfit1}
              alt="crossfit"
            />
            <h4 className="className">Crossfit</h4>
            <p className="ClassAbout">CrossFit is a strength and conditioning program for
             virtually anyone who is interested in being healthy and fit. Our member Athletes 
             range in ages from young to old and begin at all levels of physical fitness.</p>
          </Col>
                  <Col xs={6} sm={4} md={3} className="ClassInfo">
            <img
              className="ClassImg"
              src={weightloss}
              alt="weightloss"
            />
            <h4 className="className">Weight loss</h4>
            <p className="ClassAbout">When you want to lose weight, a fitness class is the
             perfect way to reach your goal. So what’s the secret to weight loss? Classes
              that torch calories, increase your strength, and that keep your metabolism 
              revving long after you’ve left the gym.</p>
          </Col>
          
        </Row>
      </div>
    );
  }
}

export default AvailableClasses;
