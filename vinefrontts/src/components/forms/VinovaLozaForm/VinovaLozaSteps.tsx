import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./VinovaLozaCSS.css"
import VinovaLozaForm from "./VinovaLozaForm";
import VinovaLozaSifrarnik from "./VinovaLozaSifrarnik";

const { Step } = Steps;

const VinovaLozaSteps: FunctionComponent = () =>{

    const [currentStep,setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);

    const changeStepForUpdate = (step: number,) => {
        //setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }
    const changeStep =(step:number) =>{
        setCurrentStep(step);
        setIsUpdate(false);
    }

    const renderForStep = () =>{
        if(currentStep === 0){
            return <VinovaLozaForm isUpdate={false} />
        }else{
           return <VinovaLozaSifrarnik />
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={ isUpdate ? constant.VINOVALOZA_STEPS_NASLOV_AUZIRANJE: constant.VINOVALOZA_STEPS_NASLOV_UNOS} status="process"/>
                <Step title={constant.VINOVALOZA_SIFRARNIK_NASLOV} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    )

}
export default VinovaLozaSteps;
