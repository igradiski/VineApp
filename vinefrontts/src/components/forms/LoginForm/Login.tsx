import  {  FunctionComponent } from "react";
import 'antd/dist/antd.css';
import "./LoginCSS.css"
import { Form, Input, Button} from 'antd';
import constant from "../../../constantsUI/constantsUI";

import { useAppDispatch } from '../../../hooks';
import { logUser } from "../../../feature/userLogin/userLogin";


const Login: FunctionComponent = () => {
    const dispatch = useAppDispatch();
    return (
        <div className="login-div">
        <h1 className="form-title">Prijava</h1>
        <Form
            name="basic"
        >
            <Form.Item
                label={constant.LABEL_USERNAME}
                name={constant.PLACEHOLDER_USERNAME}
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_USERNAME,
                    },
                ]}
            >
                <Input className="input-login" />
            </Form.Item>

            <Form.Item
                label={constant.LABEL_PASSWORD}
                name="password"
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_PASSWORD,
                    },
                ]}
            >
                <Input.Password className="input-login" />
            </Form.Item>

            <Form.Item
            >
                <Button 
                type="primary" 
                htmlType="submit"
                onClick={() => dispatch(logUser())}
                className="prijava-button"
                >
                    {constant.BUTTON_LOGIN}
                </Button>
            </Form.Item>
        </Form>
        </div>
    );
}
export default Login;