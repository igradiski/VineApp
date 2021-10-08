import axiosInstance from "../axios/axiosInstance";
import ITipSredstvaData from "../types/TipSredstvaType";




class TipSredstvaService{

    constructor() {
    }
    addTipSredstva(data: ITipSredstvaData){
        axiosInstance.post("vineApp/api/noviTipSredstva",data)
        .then(response =>{
            console.log(response)
        });
    }

}

export default TipSredstvaService;