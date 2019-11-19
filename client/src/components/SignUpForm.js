import React from "react";
import {
  Form,
  Radio,
  Button,
  Input,
  Checkbox,
  Row,
  Col,
  Upload,
  Icon,
  message,
} from 'antd';
import { API_ROOT } from "../constants";
import {withRouter} from "react-router";

function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}

function beforeUpload(file) {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJpgOrPng) {
    message.error('You can only upload JPG/PNG file!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('Image must smaller than 2MB!');
  }
  return isJpgOrPng && isLt2M;
}

class SignUpForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      confirmDirty: false,
      autoCompleteResult: [],
      loading: false,
    }
  }

  handleChange = info => {
    if (info.file.status === 'uploading') {
      this.setState({ loading: true });
      return;
    }
    if (info.file.status === 'done') {
      // Get this url from response in real world.
      getBase64(info.file.originFileObj, imageUrl =>
        this.setState({
          imageUrl,
          loading: false,
        }),
      );
    }
  };

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
    const uploadButton = (
      <div>
        <Icon type={this.state.loading ? 'loading' : 'plus'} />
        <div className="ant-upload-text">Upload your image</div>
      </div>
    );
    const { imageUrl } = this.state;
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
            label="Image"
          >
            {getFieldDecorator("image",  {
              rules: [{ required: true, message: 'Please upload your image!'}],
            })(
              <Upload
                name="image"
                listType="picture-card"
                className="avatar-uploader"
                showUploadList={false}
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                beforeUpload={beforeUpload}
                onChange={this.handleChange}
              >
                {imageUrl ? <img src={imageUrl} alt="image" style={{ width: '100%' }} /> : uploadButton}
              </Upload>,
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
