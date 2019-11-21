import React from "react";
import { Route, Redirect, BrowserRouter } from 'react-router-dom';
import Home from "./Home"
import Classes from "./Classes";
import About from "./About";
import Chat from "./Chat";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import TrainerHome from "./TrainerHome";
import TraineeHome from "./TraineeHome";
import TrainerList from "./TrainerList";
import Classroom from "./Classroom";



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
    return (
      <div>
        <BrowserRouter>
          <Route exact path={'/'} component={Home}/>
          <Route path={'/classes'} exact component={Classes}/>
          <Route path={'/about'} exact component={About}/>
          <Route path={'/chat'} exact component={this.getChat}/>
          <Route path={'/signin'} exact component={SignIn}/>
          <Route path={'/signup'} exact component={SignUp}/>
          <Route path={'/trainer'} exact component={this.getTrainer()}/>
          <Route path={'/trainee'} exact component={this.getTrainee}/>
          <Route path={'/trainerinfo'} exact component={TrainerList}/>
          <Route path={'/classroom'} exact component={this.getClassroom}/>
        </BrowserRouter>
      </div>
      /**/

    );
  }
}


