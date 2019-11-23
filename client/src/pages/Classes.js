import React from "react";
import TopMenuBar from "../components/TopMenuBar"
import TopMenuBarAuth from "../components/TopMenuBarAuth";
import ClassesBanner from "../components/ClassesBanner"
import ClassesFilter from "../components/ClassesFilter"
import Footer from "../components/Footer"
const Classes = (props) =>{
    return (
        <div>
            {props.authenticated ? <TopMenuBarAuth/>:<TopMenuBar/>}
            <ClassesBanner/>
            <ClassesFilter/>
            <Footer/>
        </div>
    );
};

export default Classes;
