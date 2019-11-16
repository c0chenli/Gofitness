import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEnvelope, faPhone } from "@fortawesome/free-solid-svg-icons";

class Footer extends React.Component {

  render() {
    return(
      <div className="footer">
        <p className="title">What We Do</p>
        <p>"Help you find the best trainer for your class."</p>
        <ul>
          <li>
            <p>
              <FontAwesomeIcon icon={faEnvelope} />
            </p>
            <p>info@gofitness.com</p>
          </li>
          <li>
            <p>
              <FontAwesomeIcon icon={faPhone} />
            </p>
            <p>+1 123 456 7890</p>
          </li>
        </ul>
        <p className="copyright">Copyright&copy; GoFitness. All Rights Reserved.</p>
      </div>
    );
  }
}

export default Footer;
