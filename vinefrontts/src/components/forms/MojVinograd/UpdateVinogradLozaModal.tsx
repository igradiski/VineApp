import { FunctionComponent, useState} from "react";
import { Modal, Button, Form, Input} from 'antd';
import constant from "../../../constantsUI/constantsUI";
import IVinogradLozaData from "../../../types/IVinogradLozaData";
import VinogradLozaService from "../../../services/VinogradLozaService";

type Props = {
    isVisible: boolean;
    closeModal: () => void;
    id: string;
}

const UpdateVinogradLozaModal: FunctionComponent<Props> = ({ isVisible, closeModal, id}) => {
    const [brojLoze, setBrojLoze] = useState("");
    const [form] = Form.useForm();
    const vinogradLozaService = new VinogradLozaService();

    const successModal = () => {
        Modal.success({
            title: constant.MOJ_VINOGRAD_DODAVANJE_LOZE_USPJESNO_TITLE,
            content: constant.MOJ_VINOGRAD_DODAVANJE_LOZE_USPJESNO
        });
    }
    const errorModal = () => {
        Modal.error({
            title: constant.MOJ_VINOGRAD_DODAVANJE_LOZE_NIJE_USPJESNO_TITLE,
            content: constant.MOJ_VINOGRAD_DODAVANJE_LOZE_NIJE_USPJESNO,

        });
    }
    const updateLozaVinograd = () => {
        var data: IVinogradLozaData = {
            brojCokota: brojLoze,
            idLoza: "",
            idVinograd: "",
            nazivLoze: "",
            slikaLoze: "",        }
        vinogradLozaService.updateVinogradLoza(data,id)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })

    }
    return (
        <Modal
            visible={isVisible}
            title={constant.MOJ_VINOGRAD_MODAL_AZURIRANJE_LOZE}
            onOk={() => closeModal()}
            onCancel={() => closeModal()}>
            <Form
                form={form}
                name="basic"
                className="forma-sredstva"
                onFinish={() => form.resetFields()}>
                <Form.Item
                    label={constant.MOJ_VINOGRAD_MODAL_LOZA_FORM_BROJ}
                    name="broj"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <Input
                        className="input-login"
                        style={{ width: "45%" }}
                        value={brojLoze}
                        onChange={e => setBrojLoze(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                >

                    <Button type="primary"
                        htmlType="submit"
                        onClick={updateLozaVinograd}
                    >
                        {constant.BOLEST_BUTTON_AZURIRAJ}
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}

export default UpdateVinogradLozaModal;