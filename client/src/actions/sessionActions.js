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
                console.log(data);
                message.success('Sign In Success!');
                //Session
                const { token } = data;
                sessionService.saveSession({ token })
                    .then(() => {
                        sessionService.saveUser(data.data)
                            .then(() => {
                                history.push(`/${data.data.role}`);
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
