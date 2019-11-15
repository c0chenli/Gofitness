import React from "react";
import WrappedRegistrationForm from "../components/SignUpForm";
import pagebg from "../assets/img/pagebg.jpg"
import TopMenuBar from "../components/TopMenuBar"
import Footer from "../components/Footer"
import FormBackground from "./FormBackground"

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
