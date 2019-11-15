import React from 'react';
import pagebg from '../assets/img/pagebg.jpg';
import TopMenuBar from "../components/TopMenuBar";
import SignInForm from "../components/SignInForm"
import GoogleAuth from "../components/GoogleAuth"
import Footer from "../components/Footer"
import FormBackground from "./FormBackground"

function SignIn() {
  return(
    <div>
      <TopMenuBar />
      <FormBackground/>
      <SignInForm />
      <Footer/>
    </div>
  );
}

export default SignIn;
