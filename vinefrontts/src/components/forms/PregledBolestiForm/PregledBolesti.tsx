import { FunctionComponent} from "react";
import constant from "../../../constantsUI/constantsUI";
import "./PregledBolestiCSS.css"


const PregledBolestiForm: FunctionComponent = () => {


    //TODO ODABIR FENOFAZE BOLESTI ILI SREDSTVA
    /**
     * SREDSTVO ->  prikaze bolesti i fenofaze di se koristi
     * 
     * FENOFAZA -> prikaze sredstva i bolesti te fenofaze
     * 
     * BOLESTI -> prikaze fenofaze i sredstva za bolest
     */

    return (
        <div className="tip-sredstva-main-div">
             <h1 className="form-title">{constant.PREGLED_BOLESTI_NASLOV}</h1>
        </div>
    );

}
export default PregledBolestiForm;