import { FunctionComponent, useState,useReducer } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./FenofazaCSS.css"
import FenofazaForm from "./FenofazaForm";
import FenofazaSifrarnik from "./FenofazaSifrarnik";
import IFenofazaData from "../../../types/IFenofazaData";



const { Step } = Steps;

const FenofazaSteps: FunctionComponent = () => {

    const [currentStep, setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);
    const [updateData,setUpdateData]= useState<IFenofazaData>({name:"",date:"",timeOfUsage:"",id:""});
    
    const changeStep = (step: number) => {
        setCurrentStep(step);
        setIsUpdate(false);
        setUpdateData({name:"",date:"",timeOfUsage:"",id:""});
    }

    const changeStepForUpdate = (step: number,data:IFenofazaData) => {
        setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }

    const renderForStep = () => {
        if (currentStep === 0) {
            return <FenofazaForm isUpdate={isUpdate} updateData={updateData} />
        } else {
            return <FenofazaSifrarnik onUpdate={changeStepForUpdate} />
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={ isUpdate ? constant.FENOFAZA_AZURIRANJE_NASLOV : constant.FENOFAZA_FORMA_UNOS} status="process" />
                <Step title={constant.FENOFAZA_SIFRARNIK} status="process" />
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );

}
export default FenofazaSteps;