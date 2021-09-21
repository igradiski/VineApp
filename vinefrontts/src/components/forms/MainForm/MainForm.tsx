import  { FunctionComponent } from "react";
import 'antd/dist/antd.css';
import { Form,Button } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import { useHistory } from 'react-router-dom';


const MainForm: FunctionComponent = () => {

    const history = useHistory();

    const handleOnSubmit = (action : String) => {
        if(action.match("prijava")){
            history.push(`/login`);
        }else{
            history.push(`/register`);
        }
        
    };
    return (
        <Form
            name="basic"
        >
            <h1 className="form-title">Dobro dosli u App</h1>
            <Form.Item
            >
                <Button
                    type="primary"
                    htmlType="submit"
                    onClick={ () => handleOnSubmit("prijava")}
                >
                    {constant.PRIJAVA_BUTTON}
                </Button>
            </Form.Item>
            <Form.Item
            >
                <Button
                    type="primary"
                    htmlType="submit"
                    onClick={ () => handleOnSubmit("registracija")}
                >
                    {constant.REGISTRACIJA_BUTTON}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default MainForm;