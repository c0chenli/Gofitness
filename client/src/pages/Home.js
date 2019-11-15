import React from 'react';
import {BrowserRouter, Link} from 'react-router-dom';
import TopMenuBar from "../components/TopMenuBar"
import HomeSlider from "../components/HomeSlider"
import Footer from "../components/Footer"
import HomeClasses from "../components/HomeClasses"

const Home = () => {
  return (
      <div>
        <TopMenuBar />
        <HomeSlider />
        <HomeClasses/>
        <Footer/>
      </div>
  );
};

export default Home;
