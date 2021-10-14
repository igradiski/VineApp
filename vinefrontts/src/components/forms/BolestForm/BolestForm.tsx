import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Cascader, Modal } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import IBolestdata from "../../../types/IBolestData";
import BolestService from "../../../services/BolestService";



const BolestForm: FunctionComponent = () => {

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");

    const { TextArea } = Input;

    function successModal() {
        Modal.success({
            title: constant.UNOS_BOLEST_SUCCESS_TITLE,
            content: constant.UNOS_BOLEST_SUCCESS
        });
    }
    function errorModal() {
        Modal.error({
            title: constant.UNOS_BOLEST_ERROR_TITLE,
            content: constant.UNOS_BOLEST_ERROR,
        });
    }


    const unesiBolest = () =>{
        let data : IBolestdata = {
            name:name,
            description:description,
            date:""
        }
        let bolestService = new BolestService();
        bolestService.addBolest(data)
        .then(response =>{
            successModal()
        }).catch((error)=>{
            errorModal()
        })
    }
    return (
        <Form
            name="basic"
            className="forma-sredstva"
        >
            <h1 className="form-title">{constant.BOLEST_NASLOV}</h1>
            <Form.Item
                label={constant.BOLEST_NAZIV}
                name="name"
                rules={[
                    {
                        required: true,
                        message: constant.BOLEST_NAZIV_MESSAGE_REQUIRED,
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
                label={constant.BOLEST_OPIS}
                name="description"
                rules={[
                    {
                        required: true,
                        message: constant.BOLEST_OPIS_MESSAGE_REQUIRED,
                    },
                ]}>
                <TextArea 
                    className="text-area-4row"
                    rows={5}
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                />
            </Form.Item>
            <Form.Item
            >

                <Button type="primary"
                    htmlType="submit"
                    onClick={unesiBolest}
                >
                    {constant.SREDSTVA_UNOS_BUTTON}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default BolestForm;