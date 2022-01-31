import  {FunctionComponent,useState } from "react";
import 'antd/dist/antd.css';
import "./RegisterFormCSS.css"
import { Form, Input, Button,Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import IUserRegisterData from "../../../types/userTypes";
import UserService from "../../../services/userService";
import {useHistory } from "react-router-dom";

const RegisterForm: FunctionComponent = () => {

    const [name, setName] = useState("");
    const [surname,setSurname]= useState("");
    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [mail,setMail] = useState("");

    const history = useHistory();

    function successModal() {
        Modal.success({
            title: constant.REGISTER_MODAL_USPJEH_TITLE,
            content: constant.REGISTER_MODAL_USPJEH
        });
    }
    function errorModal() {
        Modal.error({
            title: constant.REGISTER_MODAL_FAIL_TITLE,
            content: constant.REGISTER_MODAL_FAIL,
        });
    }


    const registerUser = () => {
        const data: IUserRegisterData ={
            name:name,
            surname:surname,
            email:mail,
            password:password,
            username:username
        }
        let usrSrc : UserService = new UserService();
        usrSrc.registerUser(data)
        .then(response =>{
            if(response.status === 201){
                successModal();
                history.push('/login');
            }else{
                errorModal();
            }
        })
        .catch((reason:any) =>{
            console.log(reason)
            errorModal();
        })
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
                <Input className="input-login" 
                value={surname}
                onChange={e => setSurname(e.target.value)}
                />
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
                <Input className="input-login" 
                value={username}
                onChange={e => setUsername(e.target.value)}
                />
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
                <Input.Password className="input-login"
                value={password}
                onChange={e => setPassword(e.target.value)}
                />
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
                <Input className="input-login"
                value={mail}
                onChange={e => setMail(e.target.value)}
                />
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