import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from  "../types/IDefaultPagingData";
import ITipSredstvaData from "../types/ITipSredstvaData";



class TipSredstvaService{
    
    async getAll(){
        var promise = await axiosInstance.get("vineApp/tip_sredstva/svi-tipovi");
        return promise;
    }

    async getAllTipSredstva(data: IDefaultPagingData){
        var promise = await axiosInstance.get("vineApp/tip_sredstva/svi-tipovi-paged",{params :data});
        return promise;
    }

    async findByItemName(name:string){
        var promise = await axiosInstance.get("vineApp/tip_sredstva/tip-by-name",{params:{name}});
        return promise;
    }

    async addTipSredstva(data: ITipSredstvaData){
        var promise = await axiosInstance.post("vineApp/tip_sredstva/novi-tip",data);
        return promise;
    }
    
    async updateTipSredstva(data: ITipSredstvaData, id: any) {
        var promise = await axiosInstance.patch("vineApp/tip_sredstva/azurirani-tip",data,{params:{id}});
        return promise;
    }

    
    async deleteItemById(id:string){
        var promise = await axiosInstance.delete("vineApp/tip_sredstva/tip",{params:{id}});
        return promise;
    }

    
}

export default TipSredstvaService;