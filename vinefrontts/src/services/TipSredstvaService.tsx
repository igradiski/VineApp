import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from  "../types/IDefaultPagingData";
import ITipSredstvaData from "../types/ITipSredstvaData";



class TipSredstvaService{
    
    constructor() {
    }

    async getAll(){
        var promise = await axiosInstance.get("vineApp/tipSredstva/svi-tipovi");
        return promise;
    }

    async getAllTipSredstva(data: IDefaultPagingData){
        console.log(data.pageNo,data.pageSize)
        var promise = await axiosInstance.get("vineApp/tipSredstva/svi-tipovi-paged",{params :data});
        return promise;
    }

    async addTipSredstva(data: ITipSredstvaData){
        var promise = await axiosInstance.post("vineApp/tipSredstva/novi-tip",data);
        return promise;
    }
    
    async updateTipSredstva(data: ITipSredstvaData, id: any) {
        var promise = await axiosInstance.post("vineApp/tip_sredstva/azurirani-tip",data,{params:{id}});
        return promise;
    }

    async findByItemName(name:string){
        var promise = await axiosInstance.post("vineApp/tip_sredstva/tip-by-name",{params:{name}});
        return promise;
    }
    
    async deleteItemById(id:string){
        var promise = await axiosInstance.post("vineApp/tip_sredstva",{params:{id}});
        return promise;
    }

    
}

export default TipSredstvaService;