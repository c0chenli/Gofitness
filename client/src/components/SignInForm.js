import React, {useState} from "react";
import { Form, Icon, Input, Button, message } from 'antd';
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import { API_ROOT } from "../constants";
import "../styles/SignInForm.css";

import GoogleAuth from "./GoogleAuth";


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
    this.getConnection = this.getConnection.bind(this);
  }

  emailChange(e) {
    this.setState({email : e.target.value})
  }

  passwordChange(e) {
    this.setState({password : e.target.value})
  }

  submit() {
    this.getConnection();
  }

  getConnection() {
    let text = {email: this.state.email, password: this.state.password}
    let send = JSON.stringify(text);

    fetch(`${API_ROOT}signin`, {
      method: 'POST',
      headers: {'Content-Type':'application/json; charset=utf-8; Access-Control-Allow-Origin: *'},
      body: send
    }).then(
      res => {
        return res.json();
      }
    ).then(
      data => {
        if (data.status === 'OK') {
          message.success('Sign In Success!')
          this.props.history.push(`/${data.role}`);
        } else {
          return Promise.reject(data.status);
        }
      }
    ).catch((status) => {
       message.error(status);
    });
  }



  render() {
    const { getFieldDecorator } = this.props.form;
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
            <Button type="submit" onClick={this.submit}
                    className="login-form-button">
              Log in
            </Button>

            <p> or <Link to="/signup">register now!</Link> </p>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

const LoginForm = Form.create({ name: 'normal_login' })(SignInForm);
export default withRouter(LoginForm);
