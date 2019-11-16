import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TrainerBanner from "../components/TrainerBanner"
import Footer from "../components/Footer"

const TrainerHome = () =>{
    return (
        <div>
          <TopMenuBarAuth/>
          <TrainerBanner/>
          <Footer/>
        </div>
    );
};

export default TrainerHome;
