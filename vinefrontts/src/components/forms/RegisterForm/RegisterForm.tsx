import  {FunctionComponent } from "react";
import 'antd/dist/antd.css';
import "./RegisterFormCSS.css"
import { Form, Input, Button, Checkbox } from 'antd';
import constant from "../../../constantsUI/constantsUI";

const RegisterForm: FunctionComponent = () => {
    return (
        <Form
            name="basic"
        >
            <h1 className="form-title">{constant.LBL_REGISTRACIJA}</h1>
            <Form.Item
                label={constant.LBL_IME_KORISNIKA}
                name="name"
                rules={[
                    {
                        required: true,
                        message: constant.MESSAGE_NAME,
                    },
                ]}
            >
                <Input className="input-login" />
            </Form.Item>

            <Form.Item
                label={constant.LBL_PREZIME_KORISNIKA}
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
                <Checkbox>{constant.ZAPAMTI_ME}</Checkbox>
            </Form.Item>

            <Form.Item
            >
                <Button type="primary" htmlType="submit">
                    {constant.PRIJAVA_BUTTON}
                </Button>
            </Form.Item>
            <Form.Item
            >
                <Button type="primary" htmlType="submit">
                    {constant.REGISTRACIJA_BUTTON}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default RegisterForm;