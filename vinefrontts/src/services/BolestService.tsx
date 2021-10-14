import axiosInstance from "../axios/axiosInstance";
import IBolestdata from "../types/IBolestData";
import IDefaultPagingData from "../types/IDefaultPagingData";


class BolestService{

    constructor(){}

    async addBolest(data : IBolestdata){
        var promise = await axiosInstance.post("vineApp/api/addBolest",data);
        return promise;
    }

    async getAllBolesti(data : IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/api/getBolestiPaged",{params :data});
        return promise;
    }

    async deleteBolestByName(data:IBolestdata){
        console.log({params :data})
        var promise = await axiosInstance.delete("vineApp/api/deleteBolest",{params :data});
        return promise;
    }
}
export default BolestService;