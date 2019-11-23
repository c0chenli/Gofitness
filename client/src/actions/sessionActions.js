import { sessionService } from 'redux-react-session';
import {API_ROOT} from "../constants";
import {message} from "antd";

export const login = (email, password, history) => {
    let text = {email: email, password: password}
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
                message.success('Sign In Success!');
                const response = {
                    token: '1a2b3c4d',
                    data: {
                        email: 'gg',
                        Name: 'test',
                        role: 'trainee'
                    },
                    status: 200
                };
                const response1 = new Promise(resolve => setTimeout(resolve(response), 1000));
                //Session
                const { token } = response1;
                sessionService.saveSession({ token })
                    .then(() => {
                        sessionService.saveUser(data.data)
                            .then(() => {
                                history.push(`/${data.role}`);
                            }).catch(err => console.error(err));
                    }).catch(err => console.error(err));

            } else {
                return Promise.reject(data.status);
            }
        }
    ).catch((status) => {
        message.error(status);
    });
};
