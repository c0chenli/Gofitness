import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import Footer from "../components/Footer"
import DisplayCalendar from "../components/DisplayCalendar";
import Iframe from 'react-iframe'
import {API_ROOT} from "../constants";
import {message} from "antd";
import {sessionService} from "redux-react-session";

class Classroom extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            roomId: 'DEFAULT_ROOM'
        };
        this.fetchRoomId = this.fetchRoomId.bind(this);
    }


    fetchRoomId() {

        fetch(`${API_ROOT}getRoomId${this.props.history.location.search}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
            },
        }).then(res => res.json()).then(
            data => {
                if (data.status === 'OK') {
                    this.setState({
                        roomId: data.room_id,
                    });
                }else{
                    message.warning('Getting Room ID failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    componentDidMount () {
        /*setTimeout(() => {
            document.getElementById('myId').contentDocument.getElementById('otEmbedContainer').style.display = "inline-flex";
        }, 1000);*/
        this.fetchRoomId();
    }
    render(){
        console.log('classroom',this.props.history.location.search);
        console.log(this.state.roomId);
        return (
            <div>
                <TopMenuBarAuth/>
                {/*<div id="otEmbedContainer"  style={divStyle}/>*/}
                <div>
                    <Iframe url={`https://tokbox.com/embed/embed/ot-embed.js?embedId=a8621673-79b4-4297-be2f-8b3a33990e55&room=${this.state.roomId}&iframe=true`}
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
