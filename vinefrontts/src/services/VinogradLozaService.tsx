import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";
import IVinogradLozaData from "../types/IVinogradLozaData";

class  VinogradLozaService{
    
    async updateVinogradLoza(data: IVinogradLozaData,id:string) {
        var promise = await axiosInstance.patch("vineApp/vinograd-loza/loze",data,{params:{id}});
        return promise;
    }
    async deleteById(id: any) {
        var promise = await axiosInstance.delete("vineApp/vinograd-loza/loze",{params: {id}});
        return promise;
    }

    async findByVinogradPaged(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/vinograd-loza/loze",{params: data});
        return promise;
    }
    async insertNewVinogradLoza(data: IVinogradLozaData) {
        var promise = await axiosInstance.post("vineApp/vinograd-loza/vingoradHasLoza",data);
        return promise;
    }

}
export default VinogradLozaService