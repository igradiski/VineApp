import { FunctionComponent ,useState} from "react";
import { Modal, Button ,Form,Input} from 'antd';
import constant from "../../../constantsUI/constantsUI";
import IVinogradData from "../../../types/IVinogradData";
import VinogradService from "../../../services/VinogradService";
type Props = {
    isVisible:boolean;
    closeModal: () => void;
}

const InsertVinogradModal:FunctionComponent<Props> = ({isVisible,closeModal}) =>{

    const [form] = Form.useForm();
    //fejk prop
    const isUpdate =false;
    const [name,setName] = useState("");
    const [adress,setAdress] = useState("");
    const [description,setDescription] = useState("");
    const vinogradService = new VinogradService();

    const  successModal = () => {
        Modal.success({
            title: constant.MOJ_VINOGRAD_MODAL_UNOS_USPJESAN_NASLOV,
            content: constant.MOJ_VINOGRAD_MODAL_UNOS_USPJESAN
        });
    }
    const errorModal = () => {
        Modal.error({
            title: constant.MOJ_VINOGRAD_MODAL_UNOS_NIJE_USPJESAN_NASLOV,
            content: constant.MOJ_VINOGRAD_MODAL_UNOS_NIJE_USPJESAN,

        });
    }


    const unesiVinograd = () =>{
        const data: IVinogradData = {
            name:name,
            adress:adress,
            description:description,
            id:""
        }
        if(isUpdate){}
        else{
            vinogradService.insertVinograd(data)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })
        }

        closeModal();
    }

    return(
        <Modal
          visible={isVisible}
          title={constant.MOJ_VINOGRAD_MODAL_NASLOV_UNOS}
          onOk={() => closeModal()}
          onCancel={() => closeModal()}
        >
             <Form
            form={form}
            name="basic"
            className="forma-sredstva"
            onFinish={() => form.resetFields()}
        >
            <Form.Item
                label={constant.MOJ_VINOGRAD_MODAL_FORM_NAZIV}
                name="name"
                rules={[
                    {
                        required: false,
                        message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                    },
                ]}
            >
                <Input
                    className="input-login"
                    style={{width:"45%"}}
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </Form.Item>
            <Form.Item
                label={constant.MOJ_VINOGRAD_MODAL_FORM_ADRESA}
                name="adress"
                rules={[
                    {
                        required: false,
                        message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                    },
                ]}
            >
                <Input
                    className="input-login"
                    style={{width:"45%"}}
                    value={name}
                    onChange={e => setAdress(e.target.value)}
                />
            </Form.Item>
            <Form.Item
                label={constant.MOJ_VINOGRAD_MODAL_FORM_OPIS}
                name="description"
                rules={[
                    {
                        required: false,
                        message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                    },
                ]}
            >
                <Input
                    className="input-login"
                    style={{width:"45%"}}
                    value={name}
                    onChange={e => setDescription(e.target.value)}
                />
            </Form.Item>
            <Form.Item
            >

                <Button type="primary"
                    htmlType="submit"
                    onClick={unesiVinograd}
                >
                    {isUpdate ? constant.MOJ_VINOGRAD_MODAL_AZUIRANJE : constant.MOJ_VINOGRAD_MODAL_UNOS}
                </Button>
            </Form.Item>
        </Form>
          
        </Modal>
    )

}
export default InsertVinogradModal;