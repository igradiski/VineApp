import axiosInstance from "../axios/axiosInstance";
import ITipSredstvaData from "../types/TipSredstvaType";
import IDefaultPagingData from  "../types/IDefaultPagingData";



class TipSredstvaService{

    constructor() {
    }

     async addTipSredstva(data: ITipSredstvaData){
        var promise = await axiosInstance.post("vineApp/api/tipSredstva",data);
        return promise;
    }
    async getAll(){
        var promise = await axiosInstance.get("vineApp/api/tipoviSredstavaAll");
        return promise;
    }
    
    async getAllTipSredstva(data: IDefaultPagingData){
        console.log(data.pageNo,data.pageSize)
        var promise = await axiosInstance.get("vineApp/api/tipoviSredstava",{params :data});
        return promise;
    }
}

export default TipSredstvaService;