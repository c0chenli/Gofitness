import React from 'react';
import {Col} from 'reactstrap';
import { Link } from 'react-router-dom';
import '../styles/TopMenuBarAuth.css';
import LogoutButton from "./LogoutButton";

class TopMenuBarAuth extends React.Component {
  render() {
    return(
      <div className="top-menu-bar" role="navigation">
        <div >
          <div className="row">
            <div className="col-md-2">
              <div id="logo"><Link to="/">{'GoFitness'}</Link></div>
            </div>
            <Col className="col-md-10 text-right menu">
              <ul>
                <li><Link to="/">{'Home'}</Link></li>
                <li><Link to="/classes">{'Classes'}</Link></li>
                <li><Link to="/trainerinfo">{'Trainers'}</Link></li>
                <li><Link to="/chat">{'Chats'}</Link></li>
                <li><Link to="/classroom">{'Classroom'}</Link></li>
                <li><Link to="/about">{'About'}</Link></li>
                <li><Link to="/">{'My Account'}</Link></li>
                <li><LogoutButton /></li>
              </ul>
            </Col>
          </div>
        </div>
      </div>
    );
  }
}

export default TopMenuBarAuth;
