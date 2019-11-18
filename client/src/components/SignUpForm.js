import React from "react";
import {Link} from "react-router-dom";
import {
  Form,
  Radio,
  Button,
  Input,
  Checkbox,
  Row,
  Col
} from 'antd';
import 'antd/dist/antd.css';

class SignUpForm extends React.Component {


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

  render() {
    const {getFieldDecorator} = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
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
          <Form.Item label="I'm a">
            {getFieldDecorator('radio-group')(
              <Radio.Group className="inputField" >
                <Radio value="trainer">trainer</Radio>
                <Radio value="trainee">trainee</Radio>
              </Radio.Group>,
            )}
          </Form.Item>
          <Form.Item
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
          <Form.Item label="E-mail">
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
          <Form.Item label="Classes">
            {getFieldDecorator('checkbox-group', {
            })(
              <Checkbox.Group style={{ width: '100%' }}>
                <Row>
                  <Col span={8}>
                    <Checkbox value="0">Fitness</Checkbox>
                  </Col>
                  <Col span={8}>
                    <Checkbox value="1">Yoga</Checkbox>
                  </Col>
                  <Col span={8}>
                    <Checkbox value="2">Boxing</Checkbox>
                  </Col>
                  <Col span={8}>
                    <Checkbox value="3">Palates</Checkbox>
                  </Col>
                  <Col span={8}>
                    <Checkbox value="4">HIIT</Checkbox>
                  </Col>
                  <Col span={8}>
                    <Checkbox value="5">Taichi</Checkbox>
                  </Col>
                </Row>
              </Checkbox.Group>,
            )}
          </Form.Item>
          <Form.Item {...tailFormItemLayout}>
            <Button type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
        </Form>
      </div>

    );
  }
}

const WrappedRegistrationForm = Form.create({ name: 'register' })(SignUpForm);

export default WrappedRegistrationForm;
