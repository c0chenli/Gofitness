import React from "react";
import TopMenuBar from "../components/TopMenuBar";
import Footer from "../components/Footer";
import TrainerListBanner from "../components/TrainerListBanner";
import AvailableTrainers from "../components/AvailableTrainers";
import TopMenuBarAuth from "../components/TopMenuBarAuth";

const TrainerList = (props) =>{
    return (
        <div>
            {props.authenticated ? <TopMenuBarAuth/>:<TopMenuBar/>}
            <TrainerListBanner/>
            <AvailableTrainers authenticated = {props.authenticated}/>
            <Footer/>
        </div>
    );
};

export default TrainerList;
