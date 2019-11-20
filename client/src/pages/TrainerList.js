import React from "react";
import TopMenuBar from "../components/TopMenuBar";
import Footer from "../components/Footer";
import TrainerListBanner from "../components/TrainerListBanner";
import AvailableTrainers from "../components/AvailableTrainers";

const TrainerList = () =>{
    return (
        <div>
            <TopMenuBar/>
            <TrainerListBanner/>
            <AvailableTrainers/>
            <Footer/>
        </div>
    );
};

export default TrainerList;
