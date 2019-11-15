import React from 'react';
import {
  Container, Row, Col, Navbar, NavbarBrand, Nav, NavItem, NavLink, Card, CardBody, CardTitle, CardSubtitle, CardText, Button
} from 'reactstrap';
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Icon from "@material-ui/core/Icon";
//import Link from "@material-ui/core/Link";
import { Link } from 'react-router-dom'

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
              </ul>
            </Col>
          </div>
        </div>
      </div>
    );
  }
}

export default TopMenuBarAuth;
