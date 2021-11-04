import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import BolestForm from "./BolestForm";
import BolestSifrarnik from "./BolestSifrarnik";
import IBolestdata from "../../../types/IBolestData";


const { Step } = Steps;

const BolestSteps: FunctionComponent = () => {

    const [currentStep,setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);
    const [updateData,setUpdateData]= useState<IBolestdata>({name:"",date:"",description:"",id:"",base64:"",picture_name:""});

    const changeStep =(step:number) =>{
        setCurrentStep(step);
        setIsUpdate(false);
        setUpdateData({name:"",date:"",description:"",id:"",base64:"",picture_name:"" });
    }

    const changeStepForUpdate = (step: number,data:IBolestdata) => {
        setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }
    const renderForStep = () =>{
        if(currentStep === 0){
            return <BolestForm isUpdate={isUpdate} updateData={updateData}/>
        }else{
           return <BolestSifrarnik onUpdate={changeStepForUpdate}/>
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={constant.BOLEST_FORMA_UNOS} status="process"/>
                <Step title={constant.BOLEST_SIFRARNIK} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );
}
export default BolestSteps;