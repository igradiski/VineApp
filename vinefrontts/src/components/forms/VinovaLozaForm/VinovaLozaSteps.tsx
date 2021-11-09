import { FunctionComponent, useState } from "react";
import { Steps, Divider } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./VinovaLozaCSS.css"
import VinovaLozaForm from "./VinovaLozaForm";
import VinovaLozaSifrarnik from "./VinovaLozaSifrarnik";
import IVinovaLozaData from "../../../types/IVinovaLozaData";

const { Step } = Steps;

const VinovaLozaSteps: FunctionComponent = () =>{

    const [currentStep,setCurrentStep] = useState(0);
    const [isUpdate,setIsUpdate] = useState(false);

    const [updateData,setUpdateData]= useState<IVinovaLozaData>({
        base64:"",
        date:"",
        description:"",
        id:"",
        name:"",
        picture_name:""
    })

    const changeStepForUpdate = (step: number,data:IVinovaLozaData) => {
        setUpdateData(data);
        setIsUpdate(true);
        setCurrentStep(step);
    }
    const changeStep =(step:number) =>{
        setCurrentStep(step);
        setIsUpdate(false);
        setUpdateData({
            base64:"",
            date:"",
            description:"",
            id:"",
            name:"",
            picture_name:""
        });
    }

    const renderForStep = () =>{
        if(currentStep === 0){
            return <VinovaLozaForm isUpdate={isUpdate} updateData={updateData} />
        }else{
           return <VinovaLozaSifrarnik onUpdate={changeStepForUpdate} />
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
