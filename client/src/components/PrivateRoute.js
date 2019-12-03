import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import {sessionService} from "redux-react-session";

const PrivateRoute = ({ component, exact = false, path, authenticated }) => (
    <Route
        exact={exact}
        path={path}
        render={props =>
            (
                authenticated ? (
                    React.createElement(component, props)
                ) : (
                    <Redirect to={{
                        pathname: '/signin',
                        state: { from: props.location }
                    }}/>
                )
            )
        }
    />
);

export default PrivateRoute;

/* Async & await problem, solve later...

import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import {sessionService} from "redux-react-session";

const sign = (props) =>{
    return (
        <Redirect to={{
            pathname: '/signin',
            state: { from: props.location }
        }}/>
    );
}

const trainee = (props) =>{
    return (
        <Redirect to={{
            pathname: '/trainee',
            state: { from: props.location }
        }}/>
    );
}

const trainer = (props) =>{
    return (
        <Redirect to={{
            pathname: '/trainer',
            state: { from: props.location }
        }}/>
    );
}

const check = ({ component, exact = false, path, authenticated },props) =>{
    if (!authenticated){
        console.log(1);
        return sign(props);
    }else{
        sessionService.loadUser()
            .then(currentUser => {
                if(path === '/trainer'){
                    console.log(2,currentUser.role === 'trainer');
                    return currentUser.role === 'trainer' ? React.createElement(component, props) : trainee(props);
                }else if(path === '/trainee' || path === '/traineeSchedule'){
                    console.log(3);
                    return currentUser.role === 'trainee' ? (React.createElement(component, props)) : trainer(props);
                }
            }).catch(err => sign(props));
    }
}

const PrivateRoute = ({ component, exact = false, path, authenticated }) => (
    <Route
        exact={exact}
        path={path}
        render={props => (console.log(666,check({ component, exact, path, authenticated },props)))}
    />
);

export default PrivateRoute;*/

