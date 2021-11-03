import { FunctionComponent, useState, useEffect } from "react";
import constant from "../../constantsUI/constantsUI";
import DateConverter from "../../feature/dateConverter";
import { Modal} from 'antd';
import SredstvaService from "../../services/SredstvaService";


type Props ={
    visibleModal : boolean;
    closeModal : () => void;
    sredstvo :string;
}

const SredstvoModal:FunctionComponent<Props> = ({visibleModal,closeModal,sredstvo}) =>{

    const [description,setDescription] = useState("");
    const [composition,setComposition] =useState("");
    const [group,setGroup] =useState("");
    const [formulation,setFormulation] =useState("");
    const [typeOfAction,setTypeOfAction] =useState("");
    const [usage,setUsage] =useState("");
    const [concentration,setConcentration] =useState("");
    const [dosageOn100,setDosageOn100] =useState("");
    const [waiting,setWaiting] =useState("");
    const [date,setDate] =useState("");
    const [nameOfTipSredstva,setNameOfTipSredstva] =useState("");
    const datumClass = new DateConverter();
    const sredstvoService = new SredstvaService();

    const getSredstvoData = () =>{
        if(sredstvo !== ""){
            sredstvoService.getSredstvoByName(sredstvo)
        .then(response =>{
            console.log(response.data)
            setDescription(response.data.description);
            setComposition(response.data.composition);
            setGroup(response.data.group);
            setFormulation(response.data.formulation);
            setTypeOfAction(response.data.typeOfAction);
            setUsage(response.data.usage);
            setConcentration(response.data.concentration);
            setDosageOn100(response.data.dosageOn100);
            setWaiting(response.data.waiting);
            setDate(response.data.date);
            setNameOfTipSredstva(response.data.nameOfTipSredstva);
        })
        }
    }

    useEffect(() =>{
        getSredstvoData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[visibleModal])

    return (
        <Modal
            title={constant.SREDSTVO_MODAL_TITLE}
            visible={visibleModal}
            onCancel={closeModal}
            onOk={closeModal}
        >
        <div className="modal-div">
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_NAZIV}</p>
                    <p className="p-content">{sredstvo}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_OPIS}</p>
                    <p className="p-content">{description}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_SASTAV}</p>
                    <p className="p-content">{composition}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_GRUPA}</p>
                    <p className="p-content">{group}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_FORMULACIJA}</p>
                    <p className="p-content">{formulation}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_PRIMJENA}</p>
                    <p className="p-content">{typeOfAction}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_KORISTENJE}</p>
                    <p className="p-content">{usage}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_KONCENTRACIJA}</p>
                    <p className="p-content">{concentration}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_DOZIRANJE}</p>
                    <p className="p-content">{dosageOn100}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_KARENCA}</p>
                    <p className="p-content">{waiting}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.SREDSTVO_MODAL_TIP}</p>
                    <p className="p-content">{nameOfTipSredstva}</p>
                </div>
                <div className="modal-row">
                    <p className="p-bold">{constant.BOLEST_MODAL_DATUM}</p>
                    <p className="p-content">{datumClass.convertDateForTable(date)}</p>
                </div>
            </div>
        </Modal>
    )
}

export default SredstvoModal;