import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import SredstvaForm from "./SredstvaForm";
import SredstvaSifrarnik from "./SredstvaSifrarnik";
import ISredstvoData from "../../../types/ISredstvoType";


const { Step } = Steps;

const SredstvaSteps: FunctionComponent = () => {

    const [currentStep,setCurrentStep] = useState(1);
    const [isUpdate,setIsUpdate] = useState(false);
    const [updateData,setUpdateData]= useState<ISredstvoData>({
        name:"",
        description:"",
        composition:"",
        group:"",
        formulation:"",
        typeOfAction:"",
        usage:"",
        concentration:"",
        dosageOn100:"",
        waiting:"",
        typeOfMedium:"",
        id:"",
        date:"",
        tipSredstvaId:"",
        base64:"",
        picture_name:""});
        //TODO CONTEXT HOOK
    const changeStep =(step:number) =>{
        setCurrentStep(step);
        setIsUpdate(false);
        setUpdateData({
            name:"",
            description:"",
            composition:"",
            group:"",
            formulation:"",
            typeOfAction:"",
            usage:"",
            concentration:"",
            dosageOn100:"",
            waiting:"",
            typeOfMedium:"",
            id:"",
            date:"",
            tipSredstvaId:"",
            base64:"",
            picture_name:""});
    }

    const changeStepForUpdate = (step: number,data:ISredstvoData) => {
        setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }

    const renderForStep = () =>{
        if(currentStep === 0){
            return <SredstvaForm isUpdate={isUpdate} updateData={updateData}/>
        }else{
           return <SredstvaSifrarnik onUpdate={changeStepForUpdate}/>
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={ isUpdate ? constant.UNOS_SREDSTVA_FORM_UDPATE: constant.SREDSTVA_FORMA_UNOS} status="process"/>
                <Step title={constant.SREDSTVA_SIFRARNIK} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );
}
export default SredstvaSteps;