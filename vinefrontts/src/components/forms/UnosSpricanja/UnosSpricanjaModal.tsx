import { FunctionComponent, useState,useEffect } from "react";
import { Modal, Button, Form, Input, DatePicker } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import ISpricanjeData from "../../../types/ISpricanjeData";
import SpricanjeService from "../../../services/SpricanjeService";

type Props = {
    isVisible: boolean;
    isUpdate: boolean;
    updateData: ISpricanjeData;
    closeModal: () => void;
}

const UnosSpricanjaModal: FunctionComponent<Props> = ({ isVisible, isUpdate, closeModal, updateData }) => {

    const [form] = Form.useForm();
    const [description, setDescription] = useState("");
    const [water, setWater] = useState("");
    const [date, setdate] = useState("");
    const spricanjeService = new SpricanjeService();

    const  successModal = () => {
        Modal.success({
            title: constant.SPRICANJE_MODAL_UNOS_SUCCESS_TITLE,
            content: constant.SPRICANJE_MODAL_UNOS_SUCCESS
        });
    }
    const errorModal = () => {
        Modal.error({
            title: constant.SPRICANJE_MODAL_UNOS_FAIL_TITLE,
            content: constant.SPRICANJE_MODAL_UNOS_FAIL,

        });
    }

    const unesiSpricanje = () => {
        const data: ISpricanjeData = {
            date: date,
            description: description,
            id: "",
            userId: "",
            water: water
        }
        if(isUpdate){
            spricanjeService.updateSpricanje(data,updateData.id)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })
        }
        else{
            spricanjeService.insertSpricanje(data)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })
        }
    }

    useEffect(() => {
        setWater(updateData.water);
        setDescription(updateData.description);
        setdate(updateData.date)
        form.setFieldsValue({
            water: updateData.water,
            description: updateData.description,
        })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [updateData]);

    return (
        <Modal
            visible={isVisible}
            title={isUpdate ? "uPDATE": constant.SPRICANJE_MODAL_NASLOV}
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
                    label={constant.SPRICANJE_MODAL_OPIS}
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
                        style={{ width: "45%" }}
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SPRICANJE_MODAL_VODA}
                    name="water"
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
                        value={water}
                        onChange={e => setWater(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SPRICANJE_MODAL_DATUM}
                    name="date"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <DatePicker
                        style={{ width: "45%", marginRight: "10%" }}
                        picker="week"
                        format={'YYYY-MM-DDTHH:mm:ss'}
                        onChange={(value: moment.Moment | null, dateString: string) => {
                            setdate(dateString+"Z");
                        }}
                    />
                </Form.Item>
                <Form.Item>
                    <Button type="primary"
                        htmlType="submit"
                        onClick={unesiSpricanje}
                    >
                        {isUpdate ? constant.MOJ_VINOGRAD_MODAL_AZUIRANJE : constant.MOJ_VINOGRAD_MODAL_UNOS}
                    </Button>
                </Form.Item>
            </Form>

        </Modal>
    )
}

export default UnosSpricanjaModal;
