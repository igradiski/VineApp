import { FunctionComponent, useState } from "react";
import { Form, Input, Button } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./TipSredstvaCSS.css"
import TipSredstvaSifrarnik from "./TipSredstvaSifrarnik";
import ITipSredstvaData from "../../../types/TipSredstvaType";
import TipSredstvaService from "../../../services/TipSredstvaService";


const TipSredstva: FunctionComponent = () => {

    const [name, setName] = useState("");

    const addTipSredstva = () => {
        const data: ITipSredstvaData ={
            name:name,
        }
        let tipSredstvaSrc : TipSredstvaService = new TipSredstvaService();
        tipSredstvaSrc.addTipSredstva(data);
    }

    return (
        <div className="tip-sredstva-main-div">
            <Form
                name="basic"
                className="tip-sredstva-forma"
            >
                <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV}</h1>
                <Form.Item
                    label={constant.TIP_SREDSTVA_NAZIV}
                    name="name"
                    rules={[
                        {
                            required: true,
                            message: constant.TIP_SREDSTVA_NAZIV_REQ,
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
                >
                    <Button type="primary"
                        htmlType="submit"
                        onClick={addTipSredstva}
                    >
                        {constant.TIP_SREDSTVA_BUTTON_DODAJ}
                    </Button>
                </Form.Item>
            </Form>
            <TipSredstvaSifrarnik></TipSredstvaSifrarnik>

        </div>
    );
}
export default TipSredstva;