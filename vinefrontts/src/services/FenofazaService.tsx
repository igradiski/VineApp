import axiosInstance from "../axios/axiosInstance";
import IFenofazaData from "../types/IFenofazaData";



class FenofazaService {
    
    constructor(){}

    async getAllFenofaze(data: any) {
        var promise = await axiosInstance.get("vineApp/fenofaza/sve-fenofaze",{params:data});
        return promise;
    }
    
    async addFenofaza (data:IFenofazaData){
        var promise = await axiosInstance.post("vineApp/fenofaza/nova-fenofaza",data);
        return promise;
    }

    async updateFenofaza(data: IFenofazaData, id: any) {
        var promise = await axiosInstance.put("vineApp/fenofaza/auzirana-fenofaza",data,{params:{id}});
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/fenofaza/fenofaza-by-name",{params:{name}});
        return promise;
    }

    async deleteItemById(id: string) {
        var promise = await axiosInstance.delete("vineApp/fenofaza/",{params:{id}});
        return promise;
    }

}
export default FenofazaService;