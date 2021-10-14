import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import BolestForm from "./BolestForm";
import BolestSifrarnik from "./BolestSifrarnik";


const { Step } = Steps;

const BolestSteps: FunctionComponent = () => {
    const [currentStep,setCurrentStep] = useState(0);

    const changeStep =(step:number) =>{
        setCurrentStep(step);
    }
    const renderForStep = () =>{
        if(currentStep === 0){
            return <BolestForm/>
        }else{
           return <BolestSifrarnik/>
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