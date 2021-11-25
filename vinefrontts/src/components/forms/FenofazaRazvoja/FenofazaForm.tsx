import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./FenofazaCSS.css"
import IFenofazaData from "../../../types/IFenofazaData";
import FenofazaService from "../../../services/FenofazaService";


type Props = {
    isUpdate: boolean,
    updateData: IFenofazaData
}

const FenofazaForm: FunctionComponent<Props> = ({ isUpdate, updateData }) => {

    const [name, setName] = useState("");
    const [timeOfUsage, setTimeOfUsage] = useState("");
    const [form] = Form.useForm();
    const { TextArea } = Input;
    const service = new FenofazaService();
    

    function successModal() {
        Modal.success({
            title: constant.FENOFAZA_SUCCESS_TITLE,
            content: constant.FENOFAZA_SUCCESS
        });
    }
    function errorModal() {
        Modal.error({
            title: constant.FENOFAZA_ERROR_TITLE,
            content: constant.FENOFAZA_ERROR,
        });
    }
    const ocistiFormu = () =>{
        setName("");
        setTimeOfUsage("");
        form.resetFields();
    }

    const unesiFenofazu = () => {
        const data: IFenofazaData = {
            id:"",
            name: name,
            timeOfUsage: timeOfUsage,
            date: ""
        }
        
        if (isUpdate) {
            service.updateFenofaza(data,updateData.id)
            .then(response => {
                isUpdate=false;
                ocistiFormu();
                successModal();
            }).catch((error) => {
                errorModal()
            })
        } else {
            service.addFenofaza(data)
                .then(response => {
                    ocistiFormu();
                    successModal();
                }).catch((error) => {
                    errorModal()
                })
        }

    }

    useEffect(() => {
        setName(updateData.name);
        setTimeOfUsage(updateData.timeOfUsage);
        form.setFieldsValue({
            name:updateData.name,
            timeOfUsage:updateData.timeOfUsage
        })
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Form
            form={form}
            name="basic"
            className="forma-sredstva"
            onFinish={() => form.resetFields()}
        >
            <h1 className="form-title">{isUpdate ? constant.FENOFAZA_AZURIRANJE_NASLOV : constant.FENOFAZA_NASLOV}</h1>
            <Form.Item
                label={constant.FENOFAZA_FORM_NAZIV}
                name="name"
                rules={[
                    {
                        required: true,
                        message: constant.FENOFAZA_FORM_NAZIV_REQUIRED,
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
                label={constant.FENOFAZA_FORM_VRIJEME}
                name="timeOfUsage"
                rules={[
                    {
                        required: true,
                        message: constant.FENOFAZA_FORM_VRIJEME_REQUIRED,
                    },
                ]}>
                <TextArea
                    className="text-area-4row"
                    rows={5}
                    value={timeOfUsage}
                    onChange={e => setTimeOfUsage(e.target.value)}
                />
            </Form.Item>

            <Form.Item
            >

                <Button type="primary"
                    htmlType="submit"
                    onClick={unesiFenofazu}
                >
                    {isUpdate ? constant.FENOFAZA_BUTTON_AZURIRAJ : constant.FENOFAZA_BUTTON_UNOS}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default FenofazaForm;