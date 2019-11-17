import React from 'react';
import {TOKEN_KEY} from "./constants";
import {Main} from "./pages/Main"


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
        />
      </div>
      /**/

    );
  }


}

export default App;
