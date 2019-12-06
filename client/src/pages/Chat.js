import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"
import TrainerBanner from "../components/TrainerBanner";
import DisplayCalendar from "../components/DisplayCalendar";

const Chat = (props) =>{
    return (
        <div>
            <TopMenuBarAuth/>
            <DisplayCalendar act={'TraineeSchedule'} target = {props.match.params.value}/>
            <Footer/>
        </div>
    );
};

export default Chat;
