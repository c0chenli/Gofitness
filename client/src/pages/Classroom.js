import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"
import DisplayCalendar from "../components/DisplayCalendar";
import Iframe from 'react-iframe'

class Classroom extends React.Component{
    /*componentDidMount () {
        setTimeout(() => {
            document.getElementById('myId').contentDocument.getElementById('otEmbedContainer').style.display = "inline-flex";
        }, 1000);
    }*/
    render(){

        const divStyle = {
            width: '800px',
            height: '640px'
        };
        return (
            <div>
                <TopMenuBarAuth/>
                {/*<div id="otEmbedContainer"  style={divStyle}/>*/}
                <div>
                    <Iframe url="https://tokbox.com/embed/embed/ot-embed.js?embedId=a8621673-79b4-4297-be2f-8b3a33990e55&room=DEFAULT_ROOM&iframe=true"
                            width="100%"
                            height="1080px"
                            id="myId"
                            className="myClassname"
                            display="initial"
                            position="relative"
                            scrolling="auto"
                            allow="microphone; camera" />
                </div>
                <Footer/>
            </div>
        );
    }
};

export default Classroom;
