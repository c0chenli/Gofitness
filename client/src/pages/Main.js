import React from "react";
import { Route, Redirect, BrowserRouter } from 'react-router-dom';
import {Home} from "./Home"
import Classes from "./Classes";
import About from "./About";
import Chat from "./Chat";
import {SignIn} from "./SignIn";
import SignUp from "./SignUp";
import TrainerHome from "./TrainerHome";
import TraineeHome from "./TraineeHome";
import TrainerList from "./TrainerList";
import Classroom from "./Classroom";
import PrivateRoute from '../components/PrivateRoute';
import { sessionService } from 'redux-react-session';
import TraineeSchedule from "./TraineeSchedule";



export class Main extends React.Component {
  getTrainee = () => {
    return this.props.isLoggedIn ? <Redirect to="/trainee"/> : <Home
      handleSuccessfulLogin={this.props.handleSuccessfulLogin}
    />
  }

  getTrainer = () => {
    return this.props.isLoggedIn ? <Redirect to="/trainer"/> : <Home
      handleSuccessfulLogin={this.props.handleSuccessfulLogin}
    />
  }

  getChat = () => {
    return this.props.isLoggedIn ? <Redirect to="/chat"/> : <Home
      handleSuccessfulLogin={this.props.handleSuccessfulLogin}
    />
  }

  getClassroom = () => {
    return this.props.isLoggedIn ? <Redirect to="/classroom"/> : <Home
      handleSuccessfulLogin={this.props.handleSuccessfulLogin}
    />
  }


  render() {

    console.log(this.props);

    sessionService.loadSession()
        .then(currentSession => console.log(currentSession.token))
        .catch(err => console.log(err));
    sessionService.loadUser()
        .then(currentUser => console.log(currentUser))
        .catch(err => console.log(err));

    return (
      <div>
        <BrowserRouter>
          <Route exact path={'/'} render={()=><Home authenticated={this.props.authenticated}/>}/>
          <Route path={'/classes'} exact render={()=><Classes authenticated={this.props.authenticated}/>}/>
          <Route path={'/trainerinfo'} exact render={()=><TrainerList authenticated={this.props.authenticated}/>}/>
          <Route path={'/about'} exact render={()=><About authenticated={this.props.authenticated}/>}/>
          <Route path={'/signin'} exact component={SignIn}/>
          <Route path={'/signup'} exact component={SignUp} />
          {this.props.checked && <PrivateRoute exact path="/trainer" component={TrainerHome} authenticated={this.props.authenticated}/>}
          {this.props.checked && <PrivateRoute path={'/trainee'} exact component={TraineeHome} authenticated={this.props.authenticated}/>}
          {this.props.checked && <PrivateRoute path={'/traineeschedule'} component={TraineeSchedule} authenticated={this.props.authenticated}/>}
          {this.props.checked && <PrivateRoute path={'/chat'} exact component={Chat} authenticated={this.props.authenticated}/>}
          {this.props.checked && <PrivateRoute path={'/classroom'} exact component={Classroom} authenticated={this.props.authenticated}/>}
          {/*<Route path={'/trainee'} exact component={TraineeHome}/>
          <Route path={'/traineeschedule'} exact render={()=><TraineeSchedule authenticated={this.props.authenticated}/>}/>
          <Route path={'/chat'} exact component={Chat}/>
          <Route path={'/classroom'} exact component={Classroom}/>*/}
        </BrowserRouter>
      </div>
      /**/

    );
  }
}


