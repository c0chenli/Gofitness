import React from "react";
import TopMenuBar from "../components/TopMenuBarAuth"
import ClassesBanner from "../components/ClassesBanner"
//import TraineeBanner from "../components/TraineeBanner"
import ClassesFilter from "../components/ClassesFilter"
import Footer from "../components/Footer"
const Classes = () =>{
    return (
        <div>
            <TopMenuBar/>
            <ClassesBanner/>
            <ClassesFilter/>
            <Footer/>
        </div>
    );
};

export default Classes;