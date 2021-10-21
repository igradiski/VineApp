import axiosInstance from "../axios/axiosInstance";
import IFenofazaData from "../types/IFenofazaData";



class FenofazaService {
    
    constructor(){}

    async getAllFenofaze(data: any) {
        var promise = await axiosInstance.get("vineApp/api/getFenofazePaged",{params:data});
        return promise;
    }
    
    async addFenofaza (data:IFenofazaData){
        var promise = await axiosInstance.post("vineApp/api/addFenofaza",data);
        return promise;
    }

    async updateFenofaza(data: IFenofazaData, id: any) {
        var promise = await axiosInstance.put("vineApp/api/updateFenofaza",data,{params:{id}});
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/api/findFenofazaByName",{params:{name}});
        return promise;
    }

    async deleteItemById(id: string) {
        var promise = await axiosInstance.delete("vineApp/api/deleteFenofaza",{params:{id}});
        return promise;
    }

}
export default FenofazaService;