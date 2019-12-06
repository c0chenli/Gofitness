import React from "react";
import {Modal, Button, Form, message} from 'antd';
import { DatePicker, TimePicker } from 'antd';
import '../styles/PopupForm.css';
import moment from "moment";
import {API_ROOT} from "../constants";
import {withRouter} from "react-router";
import {sessionService} from "redux-react-session";
import { Radio } from 'antd';

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
        value: 1,
    };

    onValueChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };

    showModal = () => {
        this.setState({
            visible: true,
        });
    };

    handleOk = () => {

        this.setState({ loading: true });
        //e.preventDefault();

        sessionService.loadSession()
            .then(currentSession => {

                this.props.form.validateFieldsAndScroll((err, fieldsValue) => {
                    if (!err) {
                        const rangeTimeValue = fieldsValue['starttime'];
                        const values = {
                            trainer_email:this.props.target,
                            "start": rangeTimeValue.format('YYYY,MM,DD,HH,mm'),
                            "end": rangeTimeValue.add(60*this.state.value,'minutes').format('YYYY,MM,DD,HH,mm')
                        };
                        const send = JSON.stringify(values);
                        console.log(send);
                        fetch(`${API_ROOT}trainee/reserve`,{
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json; charset=utf-8; Access-Control-Allow-Origin: *',
                                Authorization: currentSession.token,
                            },
                            body: send,
                        }).then(res => res.json()).then(
                            data => {
                                if (data.status === 'OK') {
                                    message.success('Class Added Successfully');
                                    setTimeout(() => {
                                        this.setState({ loading: false, visible: false });
                                    }, 1000);
                                } else {
                                    return Promise.reject(data.msg);
                                }
                            }).catch((msg) => {
                            //message.error(msg);
                            this.props.history.push(`/signin`);
                        });
                    }
                    this.setState({ loading: false });
                });


            })
            .catch(err => console.log(err))




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
                    Add Class
                </Button>
                <Modal
                    visible={visible}
                    title="Add Class"
                    onOk={this.handleOk}
                    onCancel={this.handleCancel}
                    footer={[
                        <Button key="back" onClick={this.handleCancel}>
                            Cancel
                        </Button>,
                        <Button key="submit" type="primary" loading={loading} onClick={() => {this.handleOk(); setTimeout(() => {
                            this.props.callBack()
                        }, 1000);}}>
                            Submit
                        </Button>,
                    ]}
                >
                    <div>
                        <Form>
                            <Form.Item
                                className="popup-input"
                                label="Start Time"
                            >
                                {getFieldDecorator("starttime",  {
                                    rules: [{ required: true, message: 'Please select your available time!'}],
                                })(
                                    <DatePicker showTime={{ format: 'HH:mm', minuteStep: 30,  }}
                                                format="YYYY-MM-DD HH:mm"
                                                disabledDate={disabledDate}
                                                disabledTime={disabledDateTime}
                                                placeholder="Select Time" onChange={onChange} onOk={[]} />
                                )}
                            </Form.Item>
                            <Radio.Group onChange={this.onValueChange} value={this.state.value}>
                                <Radio value={1}>1 hour</Radio>
                                <Radio value={2}>2 hours</Radio>
                            </Radio.Group>
                        </Form>
                    </div>
                </Modal>
            </div>
        );
    }
}

const WrappedPopupForm = Form.create({ name: 'popup' })(PopupForm);

export default withRouter(WrappedPopupForm);
