import React from 'react';
import TopMenuBar from "../components/TopMenuBar";
import LoginForm from "../components/SignInForm"
import GoogleAuth from "../components/GoogleAuth"
import Footer from "../components/Footer"
import FormBackground from "./FormBackground"
import '../styles/SignIn.css';
import {sessionService} from "redux-react-session";
import {Redirect} from "react-router";

export class SignIn extends React.Component {


    constructor(props){
        super(props);
        this.state = {
            role : '',
        };
    }

    componentDidMount(): void {
        sessionService.loadUser()
            .then(currentUser =>
                this.setState({ role: currentUser.role}))
            .catch(err => console.log(err))
    }

  render() {
        if (this.state.role === 'trainer')
            return (
                <Redirect
                    to={{pathname: "/trainer",}}
                />
            );

        if (this.state.role === 'trainee')
          return (
              <Redirect
                  to={{pathname: "/trainee",}}
              />
          );
        return(
          <div>
            <TopMenuBar />
            <FormBackground/>
            <LoginForm handleSuccessfulLogin={this.props.handleSuccessfulLogin}/>
            <Footer/>
          </div>
        );
  }
}


