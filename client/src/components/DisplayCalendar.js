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
import '../styles/DisplayCalendar.css';
import WrappedPopupForm from "./PopupForm"
import {API_ROOT} from "../constants";
import {sessionService} from "redux-react-session";

class DisplayCalendar extends Component{

    constructor(props){
        super(props);
        this.state = {
            scheduleData : []
        }
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
        for(e of eventu){
            if ((value-e.start)*(value-e.end) <= 0) return false;
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

        sessionService.loadSession()
            .then(currentSession => this.fetchData(currentSession.token))
            .catch(err => console.log(err))
    };

    fetchData(token) {

        fetch(`${API_ROOT}trainer/getSchedule`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                Authorization: token,
            },
        }).then(res => res.json()).then(
            data => {
                this.setState({
                    scheduleData: data,
                });
            }
        ).catch((status) => {
            window.alert(status);
        });
    };

    MyCalendar() {

        console.log(this.state.scheduleData);
        console.log(events);
        console.log(eventu);
        return (
        <div className="calendar">
          <WrappedPopupForm/>
          <div className="calendar-wrapper">
            <Calendar
                localizer={this.localizer}
                events={events}
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
        return (
            <div>
                {this.MyCalendar()}
            </div>
        );
    }
};

export default DisplayCalendar;
