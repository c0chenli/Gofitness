import React from 'react';
import TopMenuBar from "../components/TopMenuBar"
import HomeSlider from "../components/HomeSlider"
import Footer from "../components/Footer"
import HomeClasses from "../components/HomeClasses"
import HomeTrainers from "../components/HomeTrainers"
import TopMenuBarAuth from "../components/TopMenuBarAuth";

export class Home extends React.Component {
  render() {
    return (
      <div>
          {this.props.authenticated ? <TopMenuBarAuth/>:<TopMenuBar/>}
        <HomeSlider />
        <HomeClasses/>
        <HomeTrainers/>
        <Footer/>
      </div>
    );
  }
}
