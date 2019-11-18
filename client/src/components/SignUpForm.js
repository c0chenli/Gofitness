import React from "react";
import {
  Form,
  Radio,
  Button,
  Input,
  Checkbox,
  Row,
  Col
} from 'antd';
import { API_ROOT } from "../constants";
import {withRouter} from "react-router";

class SignUpForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      confirmDirty: false,
      autoCompleteResult: [],

    }
  }

  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  }

  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }

  validateToNextPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        if (values.role === 'trainer') {
          fetch(`${API_ROOT}signup`, {
            method: 'POST',
            headers: {'Content-Type':'application/json; charset=utf-8; Access-Control-Allow-Origin: *'},
            body: JSON.stringify({
              firstname: values.firstname,
              lastname: values.lastname,
              email: values.email,
              password: values.password,
              categories: values.categories,
              role: values.role,
            }),
          }).then(res => res.json()).then(
            data => {
              if (data.status === 'OK') {
                window.alert('Registeration Completed');
                this.props.history.push(`/signin`);
              } else {
                return Promise.reject(data.status);
              }
            }
          ).catch((status) => {
            window.alert(status);
          });
        } else {
          fetch(`${API_ROOT}signup`, {
            method: 'POST',
            headers: {'Content-Type':'application/json; charset=utf-8; Access-Control-Allow-Origin: *'},
            body: JSON.stringify({
              firstname: values.firstname,
              lastname: values.lastname,
              email: values.email,
              password: values.password,
              role: values.role,
            }),
          }).then(res => res.json()).then(
            data => {
              if (data.status === 'OK') {
                window.alert('Registeration Completed');
                this.props.history.push(`/signin`);
              } else {
                return Promise.reject(data.status);
              }
            }
          ).catch((status) => {
            window.alert(status);
          });
        }

      }
    });
  }

  render() {
    const {getFieldDecorator, getFieldValue} = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8, offset: 1},
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 14, offset: 1 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };

    return (
      <div className="signUpForm">
        <Form {...formItemLayout} onSubmit={this.handleSubmit}>
          <div id="form-message"><a href="/flagcamp/gofitness-web/">{'Great to have you!'}</a></div>
          <Form.Item
            className="input-field"
            label="I'm a"
          >
            {getFieldDecorator("role",  {
              initialValue: 'trainee',
              rules: [{ required: true, message: 'Please select your role!'}],
            })(
              <Radio.Group onClick={this.onClick} value={this.state.value}>
                <Radio value="trainer">trainer</Radio>
                <Radio value="trainee">trainee</Radio>
              </Radio.Group>,
            )}
          </Form.Item>
          <Form.Item
            className="input-field"
            label="First Name"
          >
            {getFieldDecorator('firstname', {
              rules: [{ required: true, message: 'Please input your first name!' }],
            })(
              <Input
                placeholder="First Name"
              />
            )}
          </Form.Item>
          <Form.Item
            className="input-field"
            label="Last Name"
          >
            {getFieldDecorator('lastname', {
              rules: [{ required: true, message: 'Please input your last name!' }],
            })(
              <Input
                placeholder="Last Name"
              />
            )}
          </Form.Item>
          <Form.Item
            className="input-field"
            label="E-mail"
          >
            {getFieldDecorator('email', {
              rules: [
                {
                  type: 'email',
                  message: 'The input is not valid E-mail!',
                },
                {
                  required: true,
                  message: 'Please input your E-mail!',
                },
              ],
            })(
              <Input
                placeholder="Email"
              />
              )}
          </Form.Item>
          <Form.Item
            {...formItemLayout}
            className="input-field"
            label="Password"
          >
            {getFieldDecorator('password', {
              rules: [{
                required: true, message: 'Please input your password!',
              }, {
                validator: this.validateToNextPassword,
              }],
            })(
              <Input placeholder="Password" type="password" />
            )}
          </Form.Item>
          <Form.Item
            {...formItemLayout}
            className="input-field"
            label="Confirm Password"
          >
            {getFieldDecorator('confirm', {
              rules: [{
                required: true, message: 'Please confirm your password!',
              }, {
                validator: this.compareToFirstPassword,
              }],
            })(
              <Input type="password" onBlur={this.handleConfirmBlur} placeholder="Password Again"/>
            )}
          </Form.Item>
          <Form.Item
            className="input-field"
            label="Classes"
            style={{display: getFieldValue('role') === 'trainer' ? 'block' : 'none'}}
          >
            {getFieldDecorator('categories', {
              rules:[{
                required: getFieldValue('role') === 'trainer' ? true : false,
                message: 'Please select your training classes!'}],
            })(
              <Checkbox.Group style={{ width: '100%' }}>
                <Row>
                  <Col span={10}>
                    <Checkbox value="Fitness">Fitness</Checkbox>
                  </Col>
                  <Col span={10}>
                    <Checkbox value="Yoga">Yoga</Checkbox>
                  </Col>
                  <Col span={10}>
                    <Checkbox value="Boxing">Boxing</Checkbox>
                  </Col>
                  <Col span={10}>
                    <Checkbox value="Palates">Palates</Checkbox>
                  </Col>
                  <Col span={10}>
                    <Checkbox value="HIIT">HIIT</Checkbox>
                  </Col>
                  <Col span={10}>
                    <Checkbox value="Taichi">Taichi</Checkbox>
                  </Col>
                </Row>
              </Checkbox.Group>,
            )}
          </Form.Item>
          <Form.Item {...tailFormItemLayout}
                     className="input-field"
          >
            <Button className="register-btn" type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
        </Form>
      </div>

    );
  }
}

const WrappedRegistrationForm = Form.create({ name: 'register' })(SignUpForm);

export default withRouter(WrappedRegistrationForm);
