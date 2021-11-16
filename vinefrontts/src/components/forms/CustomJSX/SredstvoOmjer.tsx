import { useState } from "react";
import { FullscreenOutlined, ArrowUpOutlined, ArrowDownOutlined } from '@ant-design/icons';
import constants from "../../../constantsUI/constantsUI";
import { Modal, Card, Statistic, Divider } from 'antd';
import "./SredstvoOmjerCSS.css"
import SpricanjeService from "../../../services/SpricanjeService";
import SpricanjeSredstvoService from "../../../services/SpricanjeSredstvoService";

const SredstvoOmjer = (text:any,record:any) => {

    const [popVisible, setPopVisible] = useState(false);
    const [mojaDoza,setMojaDoza] = useState("");
    const [mojaDoza100,setsetMojaDoza100] = useState("");
    const [preporucenaDoza,setPreporucenaDoza] = useState("");
    const [preporucenaDoza100,setPreporucenaDoza100] = useState("");
    const [percentage,setPercentage] = useState("");

    const spricanjeSredstvo = new SpricanjeSredstvoService();

    const openCard = () => {
        setPopVisible(true);
        spricanjeSredstvo.getOmjeri(record.sredstvo,record.spricanjeId)
        .then(response =>{
            setMojaDoza(response.data.myDose);
            setsetMojaDoza100(response.data.myDoseOn100);
            setPreporucenaDoza(response.data.dose);
            setPreporucenaDoza100(response.data.doseOn100);
            setPercentage(response.data.percentage);
        })
    }

    const getValue = () => {
        
        if(mojaDoza < preporucenaDoza){
            return (
                <Statistic
                    title="Manje od preporučenog za:"
                    value={-percentage}
                    precision={2}
                    valueStyle={{ color: "red" }}
                    prefix={<ArrowDownOutlined />}
                    suffix="%"
                />
            )
        }
        else{
            return (
                <Statistic
                title="Više od preporučenog za:"
                    value={+percentage}
                    precision={2}
                    valueStyle={{ color: "green" }}
                    prefix={<ArrowUpOutlined />}
                    suffix="%"
                />
            )
        }
    }

    return (
        <div>
            {text}
            <FullscreenOutlined style={{ float: "right" }} onClick={openCard} />
            <Modal visible={popVisible}
                title={constants.SPRICANJE_OMJER_MODAL_NASLOV}
                onCancel={() => setPopVisible(false)}
                footer={null}
                style={{ textAlign: "center" }}
            >
                <div className="row">
                    <div className="column" >
                        <h3>Vaša doza</h3>
                        <p style={{fontWeight:"bold"}}>{mojaDoza} g</p>
                    </div>
                    <div className="column">
                        <h3>Preporucena doza</h3>
                        <p style={{fontWeight:"bold"}}>{preporucenaDoza} g</p>
                    </div>
                </div>
                <Divider type="horizontal" />
                <Card>
                    {getValue()}
                </Card>
                <Divider type="horizontal" />
                <div className="row" >
                    <div className="column" >
                        <h3>Vaša doza na 100l</h3>
                        <p style={{fontWeight:"bold"}}>{mojaDoza100.toString().substring(0,5)} g</p>
                    </div>
                    <div className="column">
                        <h3>Preporuceno na 100l</h3>
                        <p style={{fontWeight:"bold"}}>{preporucenaDoza100} g</p>
                    </div>
                </div>
            </Modal>
        </div>
    )

}

export default SredstvoOmjer;