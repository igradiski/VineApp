import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";
import IVinogradLozaData from "../types/IVinogradLozaData";
import IVinovaLozaData from "../types/IVinovaLozaData";

class VinovaLozaService{
    

    async getAllLozaCascader() {
        var promise = await axiosInstance.get("vineApp/vinova-loza/loze-cascader");
        return promise;
    }
    async updateLoza(data: IVinovaLozaData, id:string) {
        var promise = await axiosInstance.patch("vineApp/vinova-loza/loza",data,{params:{id}});
        return promise;
    }

    async deleteById(id: any) {
         var promise = await axiosInstance.delete("vineApp/vinova-loza/loza",{params:{id}});
        return promise;
    }

    async getAllLozaForCard(id: any) {
        var promise = await axiosInstance.get("vineApp/vinova-loza/loze-card",{params:{id}});
        return promise;
    }

    async getAllLoza(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/vinova-loza/loze",{params:data});
        return promise;
    }

    async addLoza(data: IVinovaLozaData) {
        var promise = await axiosInstance.post("vineApp/vinova-loza/loza",data);
        return promise;
    }

    
}
export default VinovaLozaService;