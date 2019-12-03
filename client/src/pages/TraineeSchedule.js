import React from "react";
import DisplayCalendar from "../components/DisplayCalendar";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"

const TraineeSchedule = () =>{
        return (
            <div>
            <TopMenuBarAuth/>
            <DisplayCalendar act={'TraineeDisplay'}/>
            <Footer/>
            </div>
        );
};

export default TraineeSchedule;
