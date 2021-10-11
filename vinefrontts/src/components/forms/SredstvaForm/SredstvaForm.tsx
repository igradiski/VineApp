import { FunctionComponent, useState } from "react";
import { Form, Input, Button } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import ITipSredstvaData from "../../../types/TipSredstvaType";
import TipSredstvaService from "../../../services/TipSredstvaService";


const SredstvaForm: FunctionComponent = () => {

    return (
        <div className="tip-sredstva-main-div">
            <Form
                name="basic"
                className="tip-sredstva-forma"
            >
                <h1 className="form-title">{constant.SREDSTVA_NASLOV}</h1>
            </Form>

        </div>
    );
}
export default SredstvaForm;