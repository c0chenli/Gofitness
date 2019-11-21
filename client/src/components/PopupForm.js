import React from "react";
import {Modal, Button, Form} from 'antd';
import { DatePicker, TimePicker } from 'antd';
import '../styles/PopupForm.css';
import moment from "moment";
import {API_ROOT} from "../constants";
import {withRouter} from "react-router";

const { RangePicker } = DatePicker;

function onChange(date, dateString) {
  console.log(date, dateString);
}

function disabledDate(current) {
  // Can not select days before today and today
  return current && current < moment().endOf('day');
}

function range(start, end, array) {
  for (let i = start; i < end; i++) {
    array.push(i);
  }
}

function disabledDateTime() {
  const result = [];
  range(0, 8, result);
  range(20, 24, result);
  return {
    disabledHours: () => {return result},
  };
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
    this.props.form.validateFieldsAndScroll((err, fieldsValue) => {
      if (!err) {
        const rangeTimeValue = fieldsValue['date'];
        const values = {
          "start": rangeTimeValue[0].format('YYYY,MM,DD,HH,mm'),
          "end": rangeTimeValue[1].format('YYYY,MM,DD,HH,mm'),
        };
        const send = JSON.stringify(values);
        console.log(send);
        fetch(`${API_ROOT}trainer/setSchedule`,{
          method: 'POST',
          headers: {'Content-Type':'application/json; charset=utf-8; Access-Control-Allow-Origin: *'},
          body: send,
        }).then(res => res.json()).then(
          data => {
            if (data.status === 'OK') {
              window.alert('Schedule Added Successfully');
              setTimeout(() => {
                this.setState({ loading: false, visible: false });
              }, 3000);
            } else {
              return Promise.reject(data.msg);
            }
          }).catch((msg) => {
          window.alert(msg);
          this.props.history.push(`/signin`);
        });
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
              Cancel
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
                  <RangePicker
                    disabledDate={disabledDate}
                    disabledTime={disabledDateTime}
                    showTime={{
                      format: 'HH:mm',
                      minuteStep: 30,
                      defaultValue: [moment('09:00', 'HH:mm'), moment('09:30', 'HH:mm')]
                    } }
                    format="YYYY-MM-DD HH:mm"
                    placeholder={['Start Time', 'End Time']}
                    onChange={onChange}
                  />,
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

export default withRouter(WrappedPopupForm);
