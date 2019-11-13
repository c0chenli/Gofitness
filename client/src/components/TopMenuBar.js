import React from 'react';
import {
  Container, Row, Col, Navbar, NavbarBrand, Nav, NavItem, NavLink, Card, CardBody, CardTitle, CardSubtitle, CardText, Button
} from 'reactstrap';
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Icon from "@material-ui/core/Icon";
import Link from "@material-ui/core/Link";

class TopMenuBar extends React.Component {
  render() {
    return(
      <div className="top-menu" role="navigation">
        <div className="container">
          <div className="row">
            <div className="col-md-2">
              <div id="logo"><a href="/flagcamp/gofitness-web/">{'GoFitness'}</a></div>
            </div>
            <Col className="col-md-10 text-right menu">
              <ul>
                <li className="active"><a href="/flagcamp/gofitness-web/">{'Home'}</a></li>
                <li><a href="/flagcamp/gofitness-web/">{'Classes'}</a></li>
                <li><a href="/flagcamp/gofitness-web/">{'Trainers'}</a></li>
                <li><a href="/flagcamp/gofitness-web/">{'About'}</a></li>
              </ul>
            </Col>
          </div>
        </div>
      </div>
    );
  }
}

export default TopMenuBar;
