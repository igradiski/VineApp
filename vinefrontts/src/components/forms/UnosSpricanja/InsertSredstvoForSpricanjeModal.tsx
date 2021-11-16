import { FunctionComponent, useState,useEffect } from "react";
import { Modal, Button, Form, Input,Cascader } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import { CascaderOptionType } from "antd/lib/cascader";
import SredstvaService from "../../../services/SredstvaService";
import "./UnosSpricanjaCSS.css";
import ISredstvoSpricanjeData from "../../../types/ISredstvoSpricanjeData";
import SpricanjeSredstvoService from "../../../services/SpricanjeSredstvoService";

type Props = {
    isVisible:boolean;
    isUpdate: boolean;
    closeModal: () => void;
    water:string;
    spricanjeId:string;
}

const InsertSredstvoForSpricanjeModal: FunctionComponent<Props> = ({isVisible,isUpdate,closeModal,water,spricanjeId}) =>{

    const [form] = Form.useForm();
    const { TextArea } = Input;

    const [options, setOptions] = useState([]);
    const [sredstvo,setSredstvo] =useState("");
    const [userLiztraza,setUserLitraza] = useState("");
    const [preporuceniUtrosak,setPreporuceniUtrosak] = useState("0");
    const [napomena,setNapomena] = useState("");

    const sredstvoService = new SredstvaService();
    const spricanjeSredstvoService = new SpricanjeSredstvoService();


    const  successModal = () => {
        Modal.success({
            title: constant.SPRICANJE_SREDSTVO_UNOS_SUCCESS_TITLE,
            content: constant.SPRICANJE_SREDSTVO_UNOS_SUCCESS
        });
    }
    const errorModal = () => {
        Modal.error({
            title: constant.SPRICANJE_SREDSTVO_UNOS_FAIL_TITLE,
            content: constant.SPRICANJE_SREDSTVO_UNOS_FAIL,

        });
    }

    const onChange = (value: any, selectedOption: any) => {
        setSredstvo(value[0])
        sredstvoService.getPreporuceniUtrosak(water,value[0])
        .then(response =>{
            setPreporuceniUtrosak(response.data)
        })
    }
    const filter = (inputValue: string, path: CascaderOptionType[]) => {
        return path.some((option: any) => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1);
    }

    const getSredstvoCascader = () => {
        sredstvoService.getAllSredstvaForCascader()
            .then(response => {
                setOptions(response.data)
            })
    }

    const unesiSredstvoZaSpricanje = () =>{
        console.log(spricanjeId)
        const data:ISredstvoSpricanjeData = {
            id:"",
            base64:"",
            karenca:"",
            napomena:napomena,
            naziv:"",
            preporuceno:"",
            sredstvo:sredstvo,
            tip:"",
            utrosak:userLiztraza,
            spricanjeId:spricanjeId
        }
        console.log(data)
        if(isUpdate){

        }else{
            spricanjeSredstvoService.addSpricanjeSredstvo(data)
            .then(response => {
                successModal();
            }).catch((error) => {
                errorModal();
            })
        }

    }

    useEffect(() => {
        getSredstvoCascader();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

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
                    label={constant.SPRICANJE_SREDSTVO_MODAL}
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
                        style={{ width: "40%" }}
                        placeholder="Please select"
                        showSearch={{ filter }}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SPRICANJE_SREDSTVO_PREPORUCENI_UTROSAK}
                    name="litraza"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <p style={{fontWeight:"bold",marginLeft:"-10%",marginTop:"1%"}}>{preporuceniUtrosak} g</p>
                </Form.Item>
                <Form.Item
                    label={constant.SPRICANJE_SREDSTVO_USER_UTROSAK}
                    name="litraza"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <Input
                        className="input-login"
                        style={{ width: "40%" }}
                        value={userLiztraza}
                        onChange={e => setUserLitraza(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SPRICANJE_SREDSTVO_USER_NAPOMENA}
                    name="napomena"
                    rules={[
                        {
                            required: false,
                            message: constant.MOJ_VINOGRAD_MODAL_UNOS_OBAVEZAN,
                        },
                    ]}
                >
                    <TextArea
                    className="text-area-4row"
                    rows={5}
                    style={{ width: "40%" }}
                    value={napomena}
                    onChange={e => setNapomena(e.target.value)}
                />
                </Form.Item>
                <Form.Item>
                    <Button type="primary"
                        htmlType="submit"
                        style={{marginLeft:"10%"}}
                        onClick={unesiSredstvoZaSpricanje}
                    >
                        {isUpdate ? constant.SPRICANJE_SREDSTVO_AZURIRAJ : constant.SPRICANJE_SREDSTVO_UNOS}
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    )

}

export default InsertSredstvoForSpricanjeModal;