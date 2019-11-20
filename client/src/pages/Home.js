import React from 'react';
import {BrowserRouter, Link} from 'react-router-dom';
import TopMenuBar from "../components/TopMenuBar"
import HomeSlider from "../components/HomeSlider"
import Footer from "../components/Footer"
import HomeClasses from "../components/HomeClasses"
import HomeTrainers from "../components/HomeTrainers"

const Home = () => {
  return (
      <div>
        <TopMenuBar />
        <HomeSlider />
        <HomeClasses/>
        <HomeTrainers/>
        <Footer/>
      </div>
  );
};

export default Home;
