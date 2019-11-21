import React from 'react';
import TopMenuBar from "../components/TopMenuBar"
import HomeSlider from "../components/HomeSlider"
import Footer from "../components/Footer"
import HomeClasses from "../components/HomeClasses"
import HomeTrainers from "../components/HomeTrainers"

export class Home extends React.Component {
  render() {
    return (
      <div>
        <TopMenuBar />
        <HomeSlider />
        <HomeClasses/>
        <HomeTrainers/>
        <Footer/>
      </div>
    );
  }
}
