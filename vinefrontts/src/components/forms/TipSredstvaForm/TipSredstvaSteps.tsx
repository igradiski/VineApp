import { FunctionComponent, useState,useReducer } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./TipSredstvaCSS.css"
import TipSredstvaForm from "./TipSredstvaForm";
import TipSredstvaSifrarnik from "./TipSredstvaSifrarnik";
import ITipSredstvaData from "../../../types/ITipSredstvaData";


const { Step } = Steps;

const TipSredstvaSteps: FunctionComponent = () => {

    const [currentStep, setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);
    const [updateData,setUpdateData]= useState<ITipSredstvaData>({name:"",date:"",id:""});
    
    const changeStep = (step: number) => {
        setCurrentStep(step);
        setIsUpdate(false);
        setUpdateData({name:"",date:"",id:""});
    }

    const changeStepForUpdate = (step: number,data:ITipSredstvaData) => {
        setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }

    const renderForStep = () => {
        if (currentStep === 0) {
            return <TipSredstvaForm isUpdate={isUpdate} updateData={updateData} />
        } else {
            return <TipSredstvaSifrarnik onUpdate={changeStepForUpdate} />
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={ isUpdate ? constant.TIP_SREDSTVA_AZURIRANJE_NASLOV : constant.TIP_SREDSTVA_FORMA_UNOS} status="process" />
                <Step title={constant.TIP_SREDSTVA_SIFRARNIK} status="process" />
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );

}
export default TipSredstvaSteps;