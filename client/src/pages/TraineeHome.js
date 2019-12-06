import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TraineeBanner from "../components/TraineeBanner"
import Footer from "../components/Footer"
import AvailableTrainers from "../components/AvailableTrainers"

const TraineeHome = (props) =>{
    return (
        <div>
            <TopMenuBarAuth role={'trainee'}/>
            <TraineeBanner/>
            <AvailableTrainers authenticated = {props.authenticated}/>
            <Footer/>
        </div>
    );
};

export default TraineeHome;
