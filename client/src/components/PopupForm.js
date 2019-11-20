import React from "react";
import {Modal, Button, Form} from 'antd';
import { DatePicker, TimePicker } from 'antd';
import '../styles/PopupForm.css';

function onChange(date, dateString) {
  console.log(date, dateString);
}

class PopupForm extends React.Component {
  state = {
    loading: false,
    visible: false,
  };

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleOk = e => {
    this.setState({ loading: true });
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
      this.setState({ loading: false });
    });
    

  };

  handleCancel = () => {
    this.setState({ visible: false });
  };

  render() {
    const {visible, loading} = this.state;
    const {getFieldDecorator} = this.props.form;
    return (
      <div>
        <Button type="primary" onClick={this.showModal}>
          Edit your schedule
        </Button>
        <Modal
          visible={visible}
          title="Select your available time"
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          footer={[
            <Button key="back" onClick={this.handleCancel}>
              Return
            </Button>,
            <Button key="submit" type="primary" loading={loading} onClick={this.handleOk}>
              Submit
            </Button>,
          ]}
        >
          <div>
            <Form>
              <Form.Item
                className="popup-input"
                label="Available Date"
              >
                {getFieldDecorator("date",  {
                  rules: [{ required: true, message: 'Please select your available date!'}],
                })(
                  <DatePicker onChange={onChange} />,
                )}
              </Form.Item>
              <Form.Item
                className="popup-input"
                label="Start Time"
              >
                {getFieldDecorator("starttime",  {
                  rules: [{ required: true, message: 'Please select your available time!'}],
                })(
                  <TimePicker use12Hours format="h:mm A" onChange={onChange} />,
                )}
              </Form.Item>
              <Form.Item
                className="popup-input"
                label="End Time"
              >
                {getFieldDecorator("endtime",  {
                  rules: [{ required: true, message: 'Please select your available time!'}],
                })(
                  <TimePicker use12Hours format="h:mm A" onChange={onChange} />,
                )}
              </Form.Item>
            </Form>
          </div>
        </Modal>
      </div>
    );
  }
}

const WrappedPopupForm = Form.create({ name: 'popup' })(PopupForm);

export default WrappedPopupForm;
