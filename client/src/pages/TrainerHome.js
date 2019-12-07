import React from "react";
import DisplayCalendar from "../components/DisplayCalendar";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TrainerBanner from "../components/TrainerBanner"
import Footer from "../components/Footer"

const TrainerHome = () =>{

    return (
        <div>
          <TopMenuBarAuth role={'trainer'}/>
          <DisplayCalendar act={'TrainerDisplay'}/>
          <Footer/>
        </div>
    );
};

export default TrainerHome;
