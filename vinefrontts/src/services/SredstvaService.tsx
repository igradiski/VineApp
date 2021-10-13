import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";
import ISredstvoData from "../types/ISredstvoType";



class SredstvaService{

    constructor() {
    }

    addSredstvo(data : ISredstvoData):void{
        axiosInstance.post("vineApp/api/addSredstvo",data).then(response =>{
            //TODO
            console.log(response)
        });
    }

    async getAllSredstva(data : IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/api/getSredstvaPage",{params :data});
        return promise;
    }
}

export default SredstvaService;