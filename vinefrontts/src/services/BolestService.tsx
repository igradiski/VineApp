import axiosInstance from "../axios/axiosInstance";
import IBolestdata from "../types/IBolestData";
import IDefaultPagingData from "../types/IDefaultPagingData";


class BolestService{
    
    async getBolestForCard(id: any) {
        var promise = await axiosInstance.get("vineApp/bolest/bolest-card",{params :{id}})
        return promise
    }
    
    async getAllBolesti(data : IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/bolest/sve-bolesti",{params :data});
        return promise;
    }

    async getAllBolestiForCascader(){
        var promise = await axiosInstance.get("vineApp/bolest/sve-bolesti-cascader");
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/bolest/bolest-by-name",{params:{name}});
        return promise;
    }

    async findByName(name: string) {
        var promise = await axiosInstance.get("vineApp/bolest/bolest-name",{params:{name}});
        return promise;
    }

    async addBolest(data : IBolestdata){
        var promise = await axiosInstance.post("vineApp/bolest/nova-bolest",data);
        return promise;
    }

    async deleteBolestById(id:string){
        var promise = await axiosInstance.delete("vineApp/bolest/",{params :{id}});
        return promise;
    }

    async updateBolest(data: IBolestdata, id: string) {
        var promise = await axiosInstance.patch("vineApp/bolest/bolest",data,{params:{id}});
        return promise;
    }

}
export default BolestService;