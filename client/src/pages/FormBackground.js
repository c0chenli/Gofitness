import React from "react";
import pagebg from "../assets/img/pagebg.jpg"

class FormBackground extends React.Component {
  render() {
    return(
      <div>
        <img
          className="page overlay"
          src={pagebg}
        />
      </div>
    );
  }
}

export default FormBackground;
