import React from "react";
import TopMenuBar from "../components/TopMenuBar";
import Footer from "../components/Footer";
import TrainerListBanner from "../components/TrainerListBanner";
import AvailableTrainers from "../components/AvailableTrainers";
import TopMenuBarAuth from "../components/TopMenuBarAuth";

const TrainerList = () =>{
    return (
        <div>
            {this.props.authenticated ? <TopMenuBarAuth/>:<TopMenuBar/>}
            <TrainerListBanner/>
            <AvailableTrainers/>
            <Footer/>
        </div>
    );
};

export default TrainerList;
