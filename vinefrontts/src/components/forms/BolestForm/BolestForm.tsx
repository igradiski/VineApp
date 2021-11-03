import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import IBolestdata from "../../../types/IBolestData";
import BolestService from "../../../services/BolestService";


type Props = {
    isUpdate: boolean,
    updateData: IBolestdata
}

const BolestForm: FunctionComponent<Props> = ({ isUpdate, updateData }) => {

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [form] = Form.useForm();
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
    const ocistiFormu = () => {
        setName("");
        setDescription("");
        form.resetFields();
    }


    const unesiBolest = () => {
        let data: IBolestdata = {
            id: "",
            name: name,
            description: description,
            date: ""
        }
        let bolestService = new BolestService();
        if (isUpdate) {
            bolestService.updateBolest(data, updateData.id)
                .then(response => {
                    isUpdate = false;
                    ocistiFormu();
                    successModal();
                }).catch((error) => {
                    errorModal()
                })
        } else {
            bolestService.addBolest(data)
                .then(response => {
                    successModal()
                }).catch((error) => {
                    errorModal()
                })
        }

    }

    useEffect(() => {
        setName(updateData.name);
        setDescription(updateData.description);
        form.setFieldsValue({
            name:updateData.name,
            description:updateData.description
        })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Form
            form ={form}
            name="basic"
            className="forma-sredstva"
            onFinish={() => form.resetFields()}
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
                    { isUpdate ? constant.BOLEST_BUTTON_AZURIRAJ:constant.SREDSTVA_UNOS_BUTTON}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default BolestForm;