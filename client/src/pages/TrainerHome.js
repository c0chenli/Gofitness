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
import moment from 'moment'
import 'react-big-calendar/lib/css/react-big-calendar.css';

const TrainerHome = () =>{


    // Setup the localizer by providing the moment (or globalize) Object
    // to the correct localizer.
    const localizer = momentLocalizer(moment) // or globalizeLocalizer
    const allViews = Object
        .keys(Views)
        .map(k => {
            console.log(Views[k]);
            return (Views[k]);
        });

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
            />
            {console.log(allViews)}
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
