import React, {useState} from "react";
import { Form, Icon, Input, Button, message } from 'antd';
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import { API_ROOT } from "../constants";
import { sessionService } from 'redux-react-session';
import "../styles/SignInForm.css";
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import * as sessionActions from '../actions/sessionActions';

import GoogleAuth from "./GoogleAuth";
import {login} from "../actions/sessionActions";


class SignInForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
    };
    this.emailChange = this.emailChange.bind(this);
    this.passwordChange = this.passwordChange.bind(this);
    this.submit = this.submit.bind(this);
  }

  emailChange(e) {
    this.setState({email : e.target.value})
  }

  passwordChange(e) {
    this.setState({password : e.target.value})
  }

  submit(history) {
    login(this.state.email,this.state.password,history)
  }


  render() {
    const { getFieldDecorator } = this.props.form;
    const SubmitButton = withRouter(({ history }) => (
        <Button type="submit" onClick={() => this.submit(history)}
                className="login-form-button">
          Log in
        </Button>
    ));


    return (
      <div className="signInForm">
        <Form className="login-form">
          <div id="form-logo"><a href="/flagcamp/gofitness-web/">{'Welcome Back!'}</a></div>
          <Form.Item>
            {getFieldDecorator('email', {
              rules: [{ required: true, message: 'Please input your Email!' }],
            })(
              <Input
                className="formInput"
                prefix={<Icon type="user" className="signInIcon" />}
                placeholder="Email"
                type="text" onChange={this.emailChange}
              />,
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: 'Please input your Password!' }],
            })(
              <Input
                className="formInput"
                prefix={<Icon type="lock" className="signInIcon" />}
                type="password" onChange={this.passwordChange}
                placeholder="Password"

              />,
            )}
          </Form.Item>
          <Form.Item className="register-link">
            <SubmitButton />
            <p> or <Link to="/signup">register now!</Link> </p>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

const LoginForm = Form.create({ name: 'normal_login' })(SignInForm);

const mapDispatch = (dispatch) => {
  return {
    actions: bindActionCreators(sessionActions, dispatch)
  };
};

export default connect(null, mapDispatch)(LoginForm);

