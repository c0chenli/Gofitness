import React from 'react';
import TopMenuBar from "../components/TopMenuBar";
import LoginForm from "../components/SignInForm"
import GoogleAuth from "../components/GoogleAuth"
import Footer from "../components/Footer"
import FormBackground from "./FormBackground"
import '../styles/SignIn.css';

function SignIn() {
  return(
    <div>
      <TopMenuBar />
      <FormBackground/>
      <LoginForm />
      <Footer/>
    </div>
  );
}

export default SignIn;
