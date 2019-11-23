import React from 'react';
import {TOKEN_KEY} from "./constants";
import {Main} from "./pages/Main";
import { connect } from 'react-redux';

class App extends React.Component {
  state = {
    isLoggedIn: Boolean(localStorage.getItem(TOKEN_KEY)),
  }

  handleSuccessfulLogin = (token) => {
    localStorage.setItem(TOKEN_KEY, token);
    this.setState({ isLoggedIn: true });
  }

  render() {

    return (
      <div>
        <Main
          handleSuccessfulLogin={this.handleSuccessfulLogin}
          isLoggedIn={this.state.isLoggedIn}
          authenticated={this.props.authenticated}
          checked={this.props.checked}
        />
      </div>

    );
  }
}

const mapState = ({ session }) => ({
  checked: session.checked,
  authenticated: session.authenticated
});

export default connect(mapState)(App);
