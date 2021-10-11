import axiosInstance from "../axios/axiosInstance";
import ITipSredstvaData from "../types/TipSredstvaType";
import ITipSredstvaPagingData from  "../types/TipSredstvaPaging";



class TipSredstvaService{

    constructor() {
    }

     addTipSredstva(data: ITipSredstvaData):void{
        axiosInstance.post("vineApp/api/noviTipSredstva",data)
        .then(response =>{
            console.log(response)
        });
    }
    
    async getAllTipSredstva(data: ITipSredstvaPagingData){
        console.log(data.pageNo,data.pageSize)
        var promise = await axiosInstance.get("vineApp/api/tipoviSredstava",{params :data});
        return promise;
    }
}

export default TipSredstvaService;