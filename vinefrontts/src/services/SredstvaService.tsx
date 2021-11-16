import axiosInstance from "../axios/axiosInstance";
import ISredstvoData from "../types/ISredstvoType";



class SredstvaService{
    async getPreporuceniUtrosak(water: string, id: any) {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/utrosak",{params:{water,id}});
        return promise;
    }
    
    async getAllSredstva(data: any) {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/sredstva",{params:data});
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/sredstva-by-name",{params:{name}});
        return promise;
    }

    async getSredstvoByName(name: string) {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/sredstva-name",{params:{name}});
        return promise;
    }
    async getSredstvoForCard(id: any) {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/sredstva-card",{params:{id}});
        return promise;
    }

    async getAllSredstvaForCascader() {
        var promise = await axiosInstance.get("vineApp/zastitno-sredstvo/sva-sredstva-cascader");
        return promise;
    }
    
    async addSredstvo (data:ISredstvoData){
        var promise = await axiosInstance.post("vineApp/zastitno-sredstvo/sredstva",data);
        return promise;
    }

    async updateSredstvo(data: ISredstvoData, id: any) {
        var promise = await axiosInstance.patch("vineApp/zastitno-sredstvo/sredstva",data,{params:{id}});
        return promise;
    }

    async deleteItemById(id: string) {
        var promise = await axiosInstance.delete("vineApp/zastitno-sredstvo/sredstva",{params:{id}});
        return promise;
    }
}

export default SredstvaService;