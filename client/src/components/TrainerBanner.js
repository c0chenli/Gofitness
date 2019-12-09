import React from "react";
import { Container, Row } from "react-bootstrap";
import bannerbg from '../assets/img/banner-bg.png';
import '../styles/Banner.css';

class TrainerBanner extends React.Component {
     formatDate(date) {
         const monthNames = [
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct",
            "Nov", "Dec"
        ];
         const day = date.getDate();
         const monthIndex = date.getMonth();
         const year = date.getFullYear();
         const hour = date.getHours();
         const mins = (date.getMinutes()<10?'0':'') + date.getMinutes();

        return hour + ':' + mins + ' / ' + monthNames[monthIndex] + ' ' + day + ' ' + year;
    }
  render() {
      let time = 'No class';
      if (this.props.start[0]){
          time = this.formatDate(this.props.start[0].start);
          /*console.log('banner',this.props.start[0].start.getDate());
          console.log('banner',this.props.start[0].start.getMonth());
          console.log('banner',this.props.start[0].start.getHours());
          console.log((this.props.start[0].start.getMinutes()<10?'0':'') + this.props.start[0].start.getMinutes());
          console.log('banner',this.props.start[0].start.toString());*/
          //time = this.props.start[0].start;
      }
    return(
      <div
        className="banner"
        style = {{ backgroundImage: `url(${ bannerbg })`,
          backgroundColor: "#fbfafa",
          backgroundRepeat: "repeat-x",
          height: "100px",
          backgroundPosition: "center",
          width: "100%",
        }}>
        <Container className="banner-div">
          <Row>
            <div className="col-sm-10">
              <h1 className="banner-title">Schedule</h1>
              <p className="banner-right">Next Class:</p>
            </div>
            <p className="appointment">{time}</p>
          </Row>
        </Container>
      </div>
    );
  }
}

export default TrainerBanner;
