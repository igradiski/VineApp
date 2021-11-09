import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";
import IVinogradLozaData from "../types/IVinogradLozaData";

class  VinogradLozaService{

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