import React, {Component} from "react";
import {
    Calendar,
    DateLocalizer,
    momentLocalizer,
    globalizeLocalizer,
    move,
    Views,
    Navigate,
    components,
} from 'react-big-calendar'
import events from '../pages/event'
import eventu from "../pages/UnavaliableTime";
import moment from 'moment'
import 'react-big-calendar/lib/css/react-big-calendar.css';
//import '../styles/react-big-calendar.css'
import '../styles/DisplayCalendar.css';
import WrappedPopupForm from "./PopupForm";
import TraineeWrappedPopupForm from "./TraineeSetSchedulePopUpForm";
import {API_ROOT} from "../constants";
import {sessionService} from "redux-react-session";
import _ from "lodash";
import {message} from "antd";

class DisplayCalendar extends Component{
    constructor(props){
        super(props);
        this.state = {
            scheduleTime : [],
            availableTime : []
        }
        this.updateAvailableTime = this.updateAvailableTime.bind(this);
    }



    // Setup the localizer by providing the moment (or globalize) Object
    // to the correct localizer.
    localizer = momentLocalizer(moment) // or globalizeLocalizer
    allViews = Object
        .keys(Views)
        .map(k => {
            return (Views[k]);
        });
    isBanned = (value) => {
        let e;
        if (this.state.availableTime === [])
            return true;
        for(e of this.state.availableTime){
            if ((value-parseInt(e.start))*(value-parseInt(e.end)+1) <= 0) return false;
        }
        return true;
    };
    TimeSlotWrapper = (props: { children: React.ReactNode, resource: null /* grid */ | undefined /* gutter */, value: Date }) => {
        if (props.resource === undefined /* gutter */ || !this.isBanned(props.value.getTime())) {
            return props.children;
        }

        const child = React.Children.only(props.children);
        return React.cloneElement(child, { className: child.props.className + ' rbc-off-range-bg' });
    };
    eventStyles = {
        reject: {
            backgroundColor:'red',
            color: 'white',
            borderRadius:0
        },
        approve: {
            backgroundColor:'blue',
            color: 'white',
            borderRadius:0
        },
        tbd: {
            backgroundColor:'green',
            color: 'white',
            borderRadius:0
        },
    };

    eventRenderProps = (event, start, end, isSelected) => {
        return {
            style: event.status === 0 ? this.eventStyles.approve: (event.status === 1 ? this.eventStyles.tbd:this.eventStyles.reject)
        }
    };

    componentDidMount() {

        if (this.props.act === 'TrainerDisplay'){
            sessionService.loadSession()
                .then(currentSession => {
                    this.fetchScheduleData(currentSession.token);
                    this.fetchAvailableData(currentSession.token);
                })
                .catch(err => console.log(err))
        }else if(this.props.act === 'TraineeDisplay'){
            sessionService.loadSession()
                .then(currentSession => {
                    this.fetchTraineeScheduleData(currentSession.token);
                })
                .catch(err => console.log(err))

        }else if(this.props.act === 'TraineeSchedule'){
            sessionService.loadSession()
                .then(currentSession => {
                    this.fetchTrainerScheduleData(currentSession.token);
                    this.fetchTrainerAvailableData(currentSession.token);
                })
                .catch(err => console.log(err))

        }else {

        }

    };

    updateTime(data){
        data.forEach((event) => {
            if (parseInt(event.start))
                event.start = new Date(parseInt(event.start));
            if (parseInt(event.end))
                event.end = new Date(parseInt(event.end));
        });
    }


    fetchTraineeScheduleData(token) {

        fetch(`${API_ROOT}trainee/getReservation`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                if (!data.status){
                    this.updateTime(data);
                    this.setState({
                        scheduleTime: data,
                    });
                }else{
                    message.warning('Loading schedule data failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    fetchTrainerScheduleData(token) {

        fetch(`${API_ROOT}trainee/getTrainerSchedule`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                if (!data.status){
                    this.updateTime(data);
                    this.setState({
                        scheduleTime: data,
                    });
                }else{
                    message.warning('Loading schedule data failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    fetchTrainerAvailableData = (token) => {

        fetch(`${API_ROOT}trainee/getTrainerAvailableTime`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                if (!data.status){
                    this.setState({
                        availableTime: data,
                    });
                }else{
                    message.warning('Loading available time data failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    fetchScheduleData(token) {

        fetch(`${API_ROOT}trainer/getSchedule`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                if (!data.status){
                    this.updateTime(data);
                    this.setState({
                        scheduleTime: data,
                    });
                }else{
                    message.warning('Loading schedule data failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

     fetchAvailableData = (token) => {

        fetch(`${API_ROOT}trainer/availableTime`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                if (!data.status){
                    this.setState({
                        availableTime: data,
                    });
                }else{
                    message.warning('Loading available time data failed.');
                    setTimeout(() => {
                        this.setState({ loading: false, visible: false });
                    }, 3000);
                }
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    updateAvailableTime(){
        console.log('updating available time.');
        sessionService.loadSession()
            .then(currentSession => {
                this.fetchAvailableData(currentSession.token);
            }).catch(err => console.log(err));
    };

    updateScheduleTime(){
        console.log('updating schedule time.');
        sessionService.loadSession()
            .then(currentSession => {
                this.fetchTraineeScheduleData(currentSession.token);
            }).catch(err => console.log(err));
    };

    TrainerDisplayCalendar() {

        console.log('schedule: ',this.state.scheduleTime);
        console.log('Available: ',this.state.availableTime);
        return (
        <div className="calendar">
          <WrappedPopupForm callBack = {this.updateAvailableTime}/>
          <div className="calendar-wrapper">
            <Calendar
                localizer={this.localizer}
                events={this.state.scheduleTime}
                step={30}
                defaultView="week"
                views={{week:true, agenda:true}}
                defaultDate={new Date()}
                startAccessor="start"
                endAccessor="end"
                eventPropGetter={this.eventRenderProps}
                components={{ timeSlotWrapper: this.TimeSlotWrapper }}
            />
          </div>
        </div>
    );};

    TraineeDisplayCalendar() {

        console.log('schedule: ',this.state.scheduleTime);
        console.log('Available: ',this.state.availableTime);
        return (
            <div className="calendar">
                <div className="calendar-wrapper">
                    <Calendar
                        localizer={this.localizer}
                        events={this.state.scheduleTime}
                        step={30}
                        defaultView="week"
                        views={{week:true, agenda:true}}
                        defaultDate={new Date()}
                        startAccessor="start"
                        endAccessor="end"
                        eventPropGetter={this.eventRenderProps}
                    />
                </div>
            </div>
        );};

    TraineeScheduleCalendar() {

        console.log('schedule: ',this.state.scheduleTime);
        console.log('Available: ',this.state.availableTime);
        return (
            <div className="calendar">
                <TraineeWrappedPopupForm callBack = {this.updateScheduleTime}/>
                <div className="calendar-wrapper">
                    <Calendar
                        localizer={this.localizer}
                        events={this.state.scheduleTime}
                        step={30}
                        defaultView="week"
                        views={{week:true, agenda:true}}
                        defaultDate={new Date()}
                        startAccessor="start"
                        endAccessor="end"
                        eventPropGetter={this.eventRenderProps}
                        components={{ timeSlotWrapper: this.TimeSlotWrapper }}
                    />
                </div>
            </div>
        );};

    render(){
        console.log(this.props.act);
        if (this.props.act === 'TrainerDisplay'){
            return (
                <div>
                    {this.TrainerDisplayCalendar()}
                </div>
            );

        }else if(this.props.act === 'TraineeDisplay'){
            return (
                <div>
                    {this.TraineeDisplayCalendar()}
                </div>
            );

        }else if(this.props.act === 'TraineeSchedule'){
            return (
                <div>
                    {this.TraineeScheduleCalendar()}
                </div>
            );
        }else {
            return (
                <div>
                    Error
                </div>
            );
        }
    }
};

export default DisplayCalendar;
