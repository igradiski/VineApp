import  {FunctionComponent,useState } from "react";
import 'antd/dist/antd.css';
import "./RegisterFormCSS.css"
import { Form, Input, Button } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import IUserRegisterData from "../../../types/userTypes";
import UserService from "../../../services/userService";

const RegisterForm: FunctionComponent = () => {

    const [name, setName] = useState("") 



    const registerUser = () => {
        const data: IUserRegisterData ={
            name:name,
            surname:"",
            email:"",
            password:"",
            username:""
        }
        let usrSrc : UserService = new UserService();
        usrSrc.registerUser(data)
    }
    return (
        <Form
            name="basic"
        >
            <h1 className="form-title">{constant.LBL_REGISTRATION}</h1>
            <Form.Item
                label={constant.LBL_USER_NAME}
                name="name"
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_NAME,
                    },
                ]}
            >
                <Input 
                className="input-login"
                value={name}
                onChange={e => setName(e.target.value)}
                 />
            </Form.Item>

            <Form.Item
                label={constant.LBL_USER_SURNAME}
                name="surname"
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_SURNAME,
                    },
                ]}
            >
                <Input className="input-login" />
            </Form.Item>

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
                label={constant.LBL_MAIL}
                name="mail"
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_MAIL,
                    },
                ]}
            >
                <Input className="input-login" />
            </Form.Item>
            <Form.Item
            >
                <Button type="primary" 
                htmlType="submit"
                onClick={registerUser}
                >
                    {constant.BUTTON_REGISTER}
                </Button>
            </Form.Item>
        </Form>
    );
}


export default RegisterForm;