import React from "react";
import WrappedRegistrationForm from "../components/SignUpForm";
import TopMenuBar from "../components/TopMenuBar";
import Footer from "../components/Footer";
import FormBackground from "./FormBackground";
import '../styles/SignUp.css';

function SignUp (){
    return (
        <div>
          <TopMenuBar/>
          <FormBackground/>
          <WrappedRegistrationForm />
          <Footer/>
        </div>
    );
}

export default SignUp;
