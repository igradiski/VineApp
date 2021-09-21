import  {  FunctionComponent } from "react";
import 'antd/dist/antd.css';
import "./LoginCSS.css"
import { Form, Input, Button, Checkbox } from 'antd';
import constant from "../../../constantsUI/constantsUI";

import { useAppDispatch } from '../../../hooks';
import { logUser,logOffUser } from "../../../feature/userLogin/userLogin";


const Login: FunctionComponent = () => {
    const dispatch = useAppDispatch();
    return (
        <Form
            name="basic"
        >
            <h1 className="form-title">Prijava</h1>
            <Form.Item
                label={constant.LABEL_USERNAME}
                name={constant.PLACEHOLDER_USERNAME}
                rules={[
                    {
                        //required: true,
                        message: constant.MESSAGE_USERNAME,
                    },
                ]}
            >
                <Input className="input-login" />
            </Form.Item>

            <Form.Item
                label={constant.LABEL_PASSWORD}
                name={constant.PLACEHOLDER_PASSWORD}
                rules={[
                    {
                       // required: true,
                        message: constant.MESSAGE_PASSWORD,
                    },
                ]}
            >
                <Input.Password className="input-login" />
            </Form.Item>

            <Form.Item
            >
                <Checkbox>{constant.ZAPAMTI_ME}</Checkbox>
            </Form.Item>

            <Form.Item
            >
                <Button 
                type="primary" 
                htmlType="submit"
                onClick={() => dispatch(logUser())}
                >
                    {constant.PRIJAVA_BUTTON}
                </Button>
            </Form.Item>
            <Form.Item
            >
                <Button 
                type="primary"
                htmlType="submit"
                onClick={() => dispatch(logOffUser())}
                 >
                    {constant.REGISTRACIJA_BUTTON}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default Login;