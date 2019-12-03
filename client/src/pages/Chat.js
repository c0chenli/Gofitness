import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"
import TrainerBanner from "../components/TrainerBanner";
import DisplayCalendar from "../components/DisplayCalendar";

const Chat = () =>{
    return (
        <div>
            <TopMenuBarAuth/>
            <DisplayCalendar act={'TraineeSchedule'}/>
            <Footer/>
        </div>
    );
};

export default Chat;
