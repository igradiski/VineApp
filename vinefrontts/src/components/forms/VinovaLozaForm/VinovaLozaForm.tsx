import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./VinovaLozaCSS.css"
import PictureUpload from "../CustomJSX/PictureUpload";
import IVinovaLozaData from "../../../types/IVinovaLozaData";
import VinovaLozaService from "../../../services/VinovaLozaService";

type Props = {
    isUpdate: boolean,
    updateData : IVinovaLozaData
}
const VinovaLozaForm:FunctionComponent<Props> = ({isUpdate,updateData}) =>{

    const { TextArea } = Input;
    const [form] = Form.useForm();
    const [name,setName] = useState("");
    const [description,setDescription] = useState("");
    const [fileBase64, setFileBase64]= useState("");
    const [fileName,setFileName] = useState("");

    const lozaService = new VinovaLozaService();

    const setFileData = (name:string,base64:string) =>{
        setFileBase64(base64);
        setFileName(name);
    }

    function successModal() {
        Modal.success({
            title: constant.VINOVALOZA_MODAL_USPJESNO_TITLE,
            content: constant.VINOVALOZA_MODAL_USPJESNO_
        });
    }
    function errorModal() {
        Modal.error({
            title: constant.VINOVALOZA_MODAL_NIJE_USPJESNO_TITLE,
            content: constant.VINOVALOZA_MODAL_NIJE_USPJESNO_,

        });
    }

    const ocistiFormu = () =>{
        setName("");
        setDescription("");
        form.resetFields();
    }

    const unesiLozu = () => {
        const data : IVinovaLozaData ={
            base64:fileBase64,
            date:"",
            description:description,
            name:name,
            id:"",
            picture_name:fileName
        }
        if(isUpdate){
            lozaService.updateLoza(data,updateData.id)
            .then(response => {
                isUpdate = false;
                ocistiFormu();
                successModal();
            }).catch((error) => {
                errorModal()
            })
        }else{
            lozaService.addLoza(data)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
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
            <h1 className="form-title">{isUpdate ? constant.VINOVALOZA_STEPS_NASLOV_AUZIRANJE : constant.VINOVALOZA_STEPS_NASLOV_UNOS}</h1>
            <Form.Item
                label={constant.SREDSTVA_NAME}
                name="name"
                rules={[
                    {
                        required: false,
                        message: constant.SREDSTVA_NAME_REQUIRED,
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
                label={constant.SREDSTVA_OPIS}
                name="description"
            >
                <TextArea
                    className="text-area-4row"
                    rows={4}
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                />
            </Form.Item>
            <Form.Item
                label={constant.VINOVALOZA_SLIKA}
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
            <Form.Item>

                <Button type="primary"
                    htmlType="submit"
                    onClick={unesiLozu}
                >
                    {isUpdate ? constant.VINOVALOZA_BUTTON_AZURIRAJ : constant.VINOVALOZA_BUTTON_UNESI}
                </Button>
            </Form.Item>
        </Form>
    )
}
export default VinovaLozaForm;