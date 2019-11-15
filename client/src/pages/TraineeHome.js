import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TraineeBanner from "../components/TraineeBanner"
import TrainerFilter from "../components/TrainerFilter"
import Footer from "../components/Footer"

const TraineeHome = () =>{
    return (
        <div>
            <TopMenuBarAuth/>
            <TraineeBanner/>
            <TrainerFilter/>
            <Footer/>
        </div>
    );
};

export default TraineeHome;
