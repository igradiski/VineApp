import { FunctionComponent } from "react";
import 'antd/dist/antd.css';
import { Button} from 'antd';
import constant from "../../../constantsUI/constantsUI";
import { useHistory } from 'react-router-dom';
import "./MainFormCSS.css"

//picture
import homePicture from "../../../images/pocetnaPicture.png";


const MainForm: FunctionComponent = () => {

    const history = useHistory();

    const handleOnSubmit = (action: String) => {
        if (action.match("prijava")) {
            history.push(`/login`);
        } else {
            history.push(`/register`);
        }

    };
    return (
        <div className="login-div">
            <h1 className="form-title">Dobro dosli u App</h1>
            <img src={homePicture} alt="s" className="picture-main" />
            <div className="buttons-div">

            
            <Button
                type="primary"
                htmlType="submit"
                className="buttons-class"
                onClick={() => handleOnSubmit("prijava")}
            >
                {constant.BUTTON_LOGIN}
            </Button>

            <Button
                type="primary"
                htmlType="submit"
                className="buttons-class"
                onClick={() => handleOnSubmit("registracija")}
            >
                {constant.BUTTON_REGISTER}
            </Button>
            </div>



        </div>
    );
}
export default MainForm;