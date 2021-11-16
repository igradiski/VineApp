import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from 'antd';
import PictureUpload from "../CustomJSX/PictureUpload";
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
    const [fileBase64, setFileBase64]= useState("");
    const [fileName,setFileName] = useState("");
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

    const setFileData = (name:string,base64:string) =>{
        setFileBase64(base64);
        setFileName(name);
    }


    const unesiBolest = () => {
        let data: IBolestdata = {
            id: "",
            name: name,
            description: description,
            date: "",
            picture_name:fileName,
            base64:fileBase64
        }
        console.log(data)
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
            console.log(data)
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
            name: updateData.name,
            description: updateData.description
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
                label="Slika bolesti:"
                name="description"
                rules={[
                    {
                        required: true,
                        message: constant.BOLEST_OPIS_MESSAGE_REQUIRED,
                    },
                ]}>
            <PictureUpload
            setFileData={setFileData} />
            </Form.Item>       
            <Form.Item >
                <Button type="primary"
                    htmlType="submit"
                    onClick={unesiBolest}
                >
                    {isUpdate ? constant.BOLEST_BUTTON_AZURIRAJ : constant.BOLEST_BUTTON_UNESI}
                </Button>
            </Form.Item>
        </Form>
    );
}
export default BolestForm;