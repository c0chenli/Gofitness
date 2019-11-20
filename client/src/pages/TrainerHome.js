import React from "react";
import TopMenuBarAuth from "../components/TopMenuBarAuth"
import TrainerBanner from "../components/TrainerBanner"
import Footer from "../components/Footer"
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
import events from './event'
import eventu from "./UnavaliableTime";
import moment from 'moment'
import 'react-big-calendar/lib/css/react-big-calendar.css';
import {forEach} from "react-bootstrap/cjs/utils/ElementChildren";

const TrainerHome = () =>{

    // Setup the localizer by providing the moment (or globalize) Object
    // to the correct localizer.
    const localizer = momentLocalizer(moment) // or globalizeLocalizer
    const allViews = Object
        .keys(Views)
        .map(k => {
            return (Views[k]);
        });
    const isBanned = (value) => {
        let e;
        for(e of eventu){
            if ((value-e.start)*(value-e.end) <= 0) return true;
        }
        return false;
    };
    const TimeSlotWrapper = (props: { children: React.ReactNode, resource: null /* grid */ | undefined /* gutter */, value: Date }) => {
        if (props.resource === undefined /* gutter */ || !isBanned(props.value.getTime())) {
            return props.children;
        }

        const child = React.Children.only(props.children);
        return React.cloneElement(child, { className: child.props.className + ' rbc-off-range-bg' });
    };
    const eventStyles = {
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
    const eventRenderProps = (event, start, end, isSelected) => {
        return {
            style: event.status === 0 ? eventStyles.approve: (event.status === 1 ? eventStyles.tbd:eventStyles.reject)
        }
    };
    const MyCalendar = props => (
        <div style={{ height: 1200 }}>

            <Calendar
                localizer={localizer}
                events={events}
                step={30}
                defaultView="week"
                views={{week:true, agenda:true}}
                defaultDate={new Date(2019, 10, 17)}
                startAccessor="start"
                endAccessor="end"
                eventPropGetter={eventRenderProps}
                components={{ timeSlotWrapper: TimeSlotWrapper }}
            />
        </div>
    );

    return (
        <div>
          <TopMenuBarAuth/>
          <TrainerBanner/>
          {MyCalendar()}
          <Footer/>
        </div>
    );
};

export default TrainerHome;
