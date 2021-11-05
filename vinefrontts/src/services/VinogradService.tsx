import IVinogradData from "../types/IVinogradData";
import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";

class VinogradService{
   
    async insertVinograd(data: IVinogradData) {
        var promise = await axiosInstance.post("vineApp/vinogradi/vinograd",data);
        return promise;
    }

    async getVinogradi(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/vinogradi/vinogradi",{params:data});
        return promise;
    }

}
export default VinogradService;