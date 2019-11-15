import React from 'react';
import {BrowserRouter, Link} from 'react-router-dom';
import TopMenuBar from "../components/TopMenuBar"
import HomeSlider from "../components/HomeSlider"
import Footer from "../components/Footer"

const Home = () => {
  return (
      <div>
        <TopMenuBar />
        <HomeSlider />
        <Footer/>
      </div>
  );
};

export default Home;
