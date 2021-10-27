import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';
import PregledBolestiISredstva from "./PregledBolestiISredstva";
import PregledBolestiForm from "../PregledBolestiForm/PregledBolesti";
import constant from "../../../constantsUI/constantsUI";
import PregledBolestiISredstvaForm from "./BolestiISredstvaForm";

const DodavanjeBolestiSredstvaSteps : FunctionComponent = () =>{

    const [currentStep,setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);
    const { Step } = Steps;

    const changeStep =(step:number) =>{
        setCurrentStep(step);
    }

    const renderForStep = () =>{
        if(currentStep === 0){
            return <PregledBolestiISredstvaForm />
        }else{
           return <PregledBolestiISredstva />
        }
    }


    return (
        <div className="bolesti-sredstva-main-div">
            <Steps className="steps-div" current={currentStep} onChange={changeStep}>
                <Step title={constant.BOLESTI_SREDSTVA_FORM_STEP} status="process"/>
                <Step title={constant.BOLESTI_SREDSTVA_SIFRARNIK_STEP} status="process"/>
            </Steps>
            <Divider />
            {renderForStep()}
        </div>
    );

}

export default DodavanjeBolestiSredstvaSteps;