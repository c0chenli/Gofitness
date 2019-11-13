import React from "react";
import { Form, Icon, Input, Button } from 'antd';

class SignInForm extends React.Component {

  render() {
    return (
      <div className="signInForm">
        <Form className="login-form">
          <div id="form-logo"><a href="/flagcamp/gofitness-web/">{'Welcome Back!'}</a></div>
          <Form.Item>
            <Input
              className="formInput"
              prefix={<Icon type="user" className="signInIcon" />}
              placeholder="Username"
            />
          </Form.Item>
          <Form.Item>
            <Input
              className="formInput"
              prefix={<Icon type="lock" className="signInIcon" />}
              type="password"
              placeholder="Password"
            />
          </Form.Item>
          <Form.Item className="register-link">
            <Button type="primary" htmlType="submit" className="login-form-button">
              Log in
            </Button>
            <p> Or <a href="">register now!</a> </p>
          </Form.Item>
        </Form>
      </div>
    );
  }
}
const WrappedNormalLoginForm = Form.create({ name: 'normal_login' })("SignInForm");



export default SignInForm;
