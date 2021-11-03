import axiosInstance from "../axios/axiosInstance";
import IFenofazaData from "../types/IFenofazaData";



class FenofazaService {
    
    async getAllFenofaze(data: any) {
        var promise = await axiosInstance.get("vineApp/fenofaza/fenofaze",{params:data});
        return promise;
    }

    async getAllFazeForCascader() {
        var promise = await axiosInstance.get("vineApp/fenofaza/sve-fenofaze-cascader");
        return promise;
    }

    async findByItemName(name: string) {
        var promise = await axiosInstance.get("vineApp/fenofaza/fenofaze-by-name",{params:{name}});
        return promise;
    }

    async findByName(faza: string) {
        var promise = await axiosInstance.get("vineApp/fenofaza/fenofaze-name",{params:{faza}});
        return promise;
    }
    
    async addFenofaza (data:IFenofazaData){
        var promise = await axiosInstance.post("vineApp/fenofaza/fenofaze",data);
        return promise;
    }

    async updateFenofaza(data: IFenofazaData, id: any) {
        var promise = await axiosInstance.patch("vineApp/fenofaza/fenofaze",data,{params:{id}});
        return promise;
    }

    async deleteItemById(id: string) {
        var promise = await axiosInstance.delete("vineApp/fenofaza/fenofaze",{params:{id}});
        return promise;
    }

}
export default FenofazaService;