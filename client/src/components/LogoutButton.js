import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router-dom';
import { logout } from '../actions/sessionActions';

const LogoutButton = ({ history, logout }) => (
    <Link to={''}
        onClick={() => logout(history)}
    >
        LOGOUT
    </Link >
);

const mapDispatch = dispatch => ({
    logout: history => dispatch(logout(history))
});

export default connect(null, mapDispatch)(withRouter(LogoutButton));
