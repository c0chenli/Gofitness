import React from "react";
import bg1 from '../assets/img/bg1.jpg';
import TopMenuBar from '../components/TopMenuBar'
import TopMenuBarAuth from "../components/TopMenuBarAuth";

const bg = {
    backgroundImage: `url(${bg1})`,
    backgroundSize: "cover",
    position:"fixed",
    top: "0",
    left: "0",
    width:"100%",
    height:"100%",
    backgroundPosition: "center 0"
}

const text = {
    width: "75%",
    height: "100%",
    margin: "300px auto",
    color:"#acbfff",
    fontFamily: "Gill Sans",
    minWidth:"500px",
    minHeight: "200px",
    boxSizing: "border-box",
    alignItems: "center",
    fontSize: "2.5vw",
    textAlign:"justify",
    zIndex:"200"
};

class About extends React.Component {
    constructor(props, context) {
        super(props);
    }

    render() {
        return (
            <div>
                <div style={bg}>
                    {this.props.authenticated ? <TopMenuBarAuth/>:<TopMenuBar/>}
                    <p style={text}>
                        GoFitness, provides one-on-one personal training online. Users could get a personalized training plan and instruction at a low cost, offering different solutions for different fitness groups.
                    </p>
                </div>
            </div>
        );
    }
}


export default About;
