import { FunctionComponent, useState, useEffect } from "react";
import { Modal, Button, Form, Input, Cascader } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import VinovaLozaService from "../../../services/VinovaLozaService";
import { CascaderOptionType } from "antd/lib/cascader";
import IVinogradLozaData from "../../../types/IVinogradLozaData";
import VinogradLozaService from "../../../services/VinogradLozaService";

type Props = {
    isVisible: boolean;
    closeModal: () => void;
    vinogradId: string;
    vinogradName: string;
}

const InsertVinogradLozaModal: FunctionComponent<Props> = ({ isVisible, closeModal, vinogradId, vinogradName }) => {

    const [vinovaLoza, setVinovaLoza] = useState("");
    const [brojLoze, setBrojLoze] = useState("");
    const [options, setOptions] = useState([]);
    const [form] = Form.useForm();
    const lozaService = new VinovaLozaService();
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

    const onChange = (value: any, selectedOption: any) => {
        setVinovaLoza(value[0])
    }
    const filter = (inputValue: string, path: CascaderOptionType[]) => {
        return path.some((option: any) => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1);
    }
    const getLozaCascader = () => {
        lozaService.getAllLozaCascader()
            .then(response => {
                setOptions(response.data)
            })
    }
    const unesiLozaVinograd = () => {
        var data: IVinogradLozaData = {
            brojCokota: brojLoze,
            idLoza: vinovaLoza,
            idVinograd: vinogradId,
            nazivLoze: "",
            slikaLoze: "",
        }
        vinogradLozaService.insertNewVinogradLoza(data)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })

    }

    useEffect(() => {
        form.setFieldsValue({
            name: vinogradName,
        })
        getLozaCascader();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);


    return (
        <Modal
            visible={isVisible}
            title={constant.MOJ_VINOGRAD_DODAVANJE_LOZE}
            onOk={() => closeModal()}
            onCancel={() => closeModal()}>
            <Form
                form={form}
                name="basic"
                className="forma-sredstva"
                onFinish={() => form.resetFields()}>
                <Form.Item
                    label={constant.MOJ_VINOGRAD_MODAL_LOZA_FORM}
                    name="loza"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <Cascader
                        options={options}
                        onChange={onChange}
                        style={{ width: "45%" }}
                        placeholder="Please select"

                        showSearch={{ filter }}
                    />
                </Form.Item>
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
                        onClick={unesiLozaVinograd}
                    >
                        {constant.MOJ_VINOGRAD_MODAL_UNOS}
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}

export default InsertVinogradLozaModal;