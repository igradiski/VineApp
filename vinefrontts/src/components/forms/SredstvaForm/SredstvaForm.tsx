import { FunctionComponent, useState ,useEffect} from "react";
import { Form, Input, Button,Cascader } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import TipSredstvaService from "../../../services/TipSredstvaService";
import ISredstvoData from "../../../types/ISredstvoType";
import SredstvaService from "../../../services/SredstvaService";


const SredstvaForm: FunctionComponent = () => {
    const { TextArea } = Input;
    const [tipSredstvaData, setTipSredstvaData] = useState([]);
    const [name,setName] = useState("");
    const [description,setDescription] = useState("");
    const [composition,setComposition] = useState("");
    const [group,setGroup] = useState("");
    const [formulation,setFormulation] = useState("");
    const [typeOfAction,setTypeOfAction] = useState("");
    const [usage,setUsage] = useState("");
    const [concentration,setConcentration] = useState("");
    const [dosageOn100,setDosageOn100] = useState("");
    const [waiting, setWaiting] = useState("2");
    const [typeOfMedium,setTypeOfMedium] = useState("");
    

    const unesiSredstvo = () =>{
        const data : ISredstvoData ={
            name : name,
            description : description,
            composition :composition,
            group :group,
            formulation : formulation,
            typeOfAction : typeOfAction,
            usage : usage,
            concentration : concentration,
            dosageOn100 : dosageOn100,
            waiting : waiting,
            typeOfMedium:typeOfMedium
        }
        let sredstvaSrc = new SredstvaService();
        sredstvaSrc.addSredstvo(data);

    }

    useEffect(() => {

        const getInitialData = async () =>{
            let tipSredstvaSrc = new TipSredstvaService();
            tipSredstvaSrc.getAll()
            .then(response=>{
                setTipSredstvaData(response.data)
            }).catch((error)=>{
                //TODO handle error
                console.log(console.error());
            })
        }
        getInitialData();
      },[]);

    return (
            <Form
                name="basic"
                className="forma-sredstva"
            >
                <h1 className="form-title">{constant.SREDSTVA_NASLOV}</h1>
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
                    label={constant.SREDSTVA_SASTAV}
                    name="composition"
                >
                    <Input
                        className="input-login"
                        value={composition}
                        onChange={e => setComposition(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_GRUPA}
                    name="group"
                >
                    <Input
                        className="input-login"
                        value={group}
                        onChange={e => setGroup(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_FORMULACIJA}
                    name="formulation"
                >
                    <Input
                        className="input-login"
                        value={formulation}
                        onChange={e => setFormulation(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_NACIN_DJELOVANJA}
                    name="typeOfAction"
                >
                    <Input
                        className="input-login"
                        value={typeOfAction}
                        onChange={e => setTypeOfAction(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_UPORABA}
                    name="usage"
                >
                    <Input
                        className="input-login"
                        value={usage}
                        onChange={e => setUsage(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_KONCENTRACIJA}
                    name="concentration"
                >
                    <Input
                        className="input-login"
                        value={concentration}
                        onChange={e => setConcentration(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_DOZIRANJE}
                    name="dosageOn100"
                >
                    <Input
                        className="input-login"
                        value={dosageOn100}
                        onChange={e => setDosageOn100(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_KARENCA}
                    name="waiting"
                    key="waiting"
                >
                    <Input
                        className="input-login"
                        value={waiting}
                        onChange={e => setWaiting(e.target.value)}
                    />
                </Form.Item>
                <Form.Item
                    label={constant.SREDSTVA_TIP_SREDSTVA}
                    name="tipSredstva"
                >
                    <Cascader placeholder="Please select" options={tipSredstvaData}
                    onChange={e => setTypeOfMedium(e[0].toString())}/>
                </Form.Item>
                <Form.Item
            >

                <Button type="primary" 
                htmlType="submit"
                onClick ={unesiSredstvo}
                >
                    {constant.SREDSTVA_UNOS_BUTTON}
                </Button>
            </Form.Item>
            </Form>
    );
}
export default SredstvaForm;