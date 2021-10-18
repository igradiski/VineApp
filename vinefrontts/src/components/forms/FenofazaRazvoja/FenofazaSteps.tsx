import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./FenofazaCSS.css"
import FenofazaForm from "./FenofazaForm";
import FenofazaSifrarnik from "./FenofazaSifrarnik";



const { Step } = Steps;

const FenofazaSteps : FunctionComponent = () =>{

    const [currentStep,setCurrentStep] = useState(0);

    const changeStep =(step:number) =>{
        setCurrentStep(step);
    }
    const renderForStep = () =>{
        if(currentStep === 0){
            return <FenofazaForm/>
        }else{
           return <FenofazaSifrarnik/>
        }
    }

    return (
        <div className="tip-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={constant.FENOFAZA_FORMA_UNOS} status="process"/>
                <Step title={constant.FENOFAZA_SIFRARNIK} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );

}
export default FenofazaSteps;