import React from "react";
import DisplayCalendar from "../components/DisplayCalendar";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"
import TrainerBanner from "../components/TrainerBanner";

const TraineeSchedule = () =>{
        return (
            <div>
            <TopMenuBarAuth role={'trainee'}/>
            <DisplayCalendar act={'TraineeDisplay'}/>
            <Footer/>
            </div>
        );
};

export default TraineeSchedule;
