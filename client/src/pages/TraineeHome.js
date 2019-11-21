import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TraineeBanner from "../components/TraineeBanner"
import Footer from "../components/Footer"
import AvailableTrainers from "../components/AvailableTrainers"

const TraineeHome = () =>{
    return (
        <div>
            <TopMenuBarAuth/>
            <TraineeBanner/>
            <AvailableTrainers/>
            <Footer/>
        </div>
    );
};

export default TraineeHome;
