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
  render() {
    return (
      <div>
        <BrowserRouter>
          <Route exact path={'/'} component={Home}/>
          <Route path={'/classes'} exact component={Classes}/>
          <Route path={'/about'} exact component={About}/>
          <Route path={'/chat'} exact component={Chat}/>
          <Route path={'/signin'} exact component={SignIn}/>
          <Route path={'/signup'} exact component={SignUp}/>
          <Route path={'/trainer'} exact component={TrainerHome}/>
          <Route path={'/trainee'} exact component={TraineeHome}/>
          <Route path={'/trainerinfo'} exact component={TrainerList}/>
          <Route path={'/classroom'} exact component={Classroom}/>
        </BrowserRouter>
      </div>
      /**/

    );
  }
}


