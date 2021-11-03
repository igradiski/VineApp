import axiosInstance from "../axios/axiosInstance";
import ISredstvoData from "../types/ISredstvoType";



class SredstvaService{
    
    async getAllSredstva(data: any) {
        var promise = await axiosInstance.get("vineApp/zastitno_sredstvo/sredstva",{params:data});
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/zastitno_sredstvo/sredstva-by-name",{params:{name}});
        return promise;
    }

    async getSredstvoByName(name: string) {
        var promise = await axiosInstance.get("vineApp/zastitno_sredstvo/sredstva-name",{params:{name}});
        return promise;
    }

    async getAllSredstvaForCascader() {
        var promise = await axiosInstance.get("vineApp/zastitno_sredstvo/sva-sredstva-cascader");
        return promise;
    }
    
    async addSredstvo (data:ISredstvoData){
        var promise = await axiosInstance.post("vineApp/zastitno_sredstvo/sredstva",data);
        return promise;
    }

    async updateSredstvo(data: ISredstvoData, id: any) {
        var promise = await axiosInstance.patch("vineApp/zastitno_sredstvo/sredstva",data,{params:{id}});
        return promise;
    }

    async deleteItemById(id: string) {
        var promise = await axiosInstance.delete("vineApp/zastitno_sredstvo/sredstva",{params:{id}});
        return promise;
    }
}

export default SredstvaService;