import axiosInstance from "../axios/axiosInstance";
import IBolestdata from "../types/IBolestData";
import IDefaultPagingData from "../types/IDefaultPagingData";


class BolestService{
    
    constructor(){}

    async addBolest(data : IBolestdata){
        var promise = await axiosInstance.post("vineApp/bolest/nova-bolest",data);
        return promise;
    }

    async getAllBolesti(data : IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/bolest/sve-bolesti",{params :data});
        return promise;
    }

    async deleteBolestById(id:string){
        var promise = await axiosInstance.delete("vineApp/bolest/",{params :{id}});
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/bolest/bolest-by-name",{params:{name}});
        return promise;
    }

    async updateBolest(data: IBolestdata, id: string) {
        var promise = await axiosInstance.put("vineApp/bolest/azurirana-bolest",data,{params:{id}});
        return promise;
    }

}
export default BolestService;