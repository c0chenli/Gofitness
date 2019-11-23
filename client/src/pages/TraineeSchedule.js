import React from "react";
import DisplayCalendar from "../components/DisplayCalendar";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"

const TraineeSchedule = () =>{
        return (
            <div>
            <TopMenuBarAuth/>
            <DisplayCalendar/>
            <Footer/>
            </div>
        );
};

export default TraineeSchedule;
