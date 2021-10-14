import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";
import ISredstvoData from "../types/ISredstvoType";



class SredstvaService{

    constructor() {
    }

    async addSredstvo(data : ISredstvoData){
        var promise = await axiosInstance.post("vineApp/api/addSredstvo",data);
        return promise;
    }

    async getAllSredstva(data : IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/api/getSredstvaPage",{params :data});
        return promise;
    }
}

export default SredstvaService;