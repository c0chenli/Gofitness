import React from 'react';
import {Col} from 'reactstrap';
import { Link } from 'react-router-dom';
import '../styles/TopMenuBarAuth.css';
import LogoutButton from "./LogoutButton";
import {sessionService} from "redux-react-session";

class TopMenuBarAuth extends React.Component {

  constructor(props){
    super(props);
    this.state = {
        role : '',
        email : '',
    };
  }

  componentDidMount(): void {
    sessionService.loadUser()
        .then(currentUser =>
            this.setState({ role: currentUser.role, email: currentUser.email}))
        .catch(err => console.log(err))
  }

  render() {
    return(
      <div className="top-menu-bar" role="navigation">
        <div >
          <div className="row">
            <div className="col-md-2">
              <div id="logo"><Link to="/">{'GoFitness'}</Link></div>
            </div>
            <Col className="col-md-10 text-right menu">
                <ul><h5>Hello {this.state.email.split('@')[0]}</h5></ul>
              <ul>

                <li><Link to="/">{'Home'}</Link></li>
                <li><Link to="/classes">{'Classes Info'}</Link></li>
                <li><Link to="/trainerinfo">{'Trainers'}</Link></li>
                {/*<li><Link to="/chat">{'Chats'}</Link></li>
                <li><Link to="/classroom">{'Classroom'}</Link></li>*/}
                <li><Link to="/about">{'About'}</Link></li>
                { this.state.role === 'trainer' ?
                     null :
                    <li><Link to="/traineeSchedule">{'Schedule'}</Link></li>}
                { this.state.role === 'trainer' ?
                    <li><Link to="/trainer">{'My Trainer Account'}</Link></li> :
                    <li><Link to="/trainee">{'My Trainee Account'}</Link></li>}
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
