import React from 'react';
import TopMenuBar from "../components/TopMenuBar";
import LoginForm from "../components/SignInForm"
import GoogleAuth from "../components/GoogleAuth"
import Footer from "../components/Footer"
import FormBackground from "./FormBackground"
import '../styles/SignIn.css';

export class SignIn extends React.Component {
  render() {
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


