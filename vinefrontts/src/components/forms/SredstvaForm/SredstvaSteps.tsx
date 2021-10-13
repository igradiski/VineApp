import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import SredstvaForm from "./SredstvaForm";
import SredstvaSifrarnik from "./SredstvaSifrarnik";


const { Step } = Steps;

const SredstvaSteps: FunctionComponent = () => {
    const [currentStep,setCurrentStep] = useState(0);

    const changeStep =(step:number) =>{
        setCurrentStep(step);
    }
    const renderForStep = () =>{
        if(currentStep === 0){
            return <SredstvaForm/>
        }else{
           return <SredstvaSifrarnik/>
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={constant.SREDSTVA_FORMA_UNOS} status="process"/>
                <Step title={constant.SREDSTVA_SIFRARNIK} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );
}
export default SredstvaSteps;