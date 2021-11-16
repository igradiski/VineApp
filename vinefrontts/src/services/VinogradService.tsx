import IVinogradData from "../types/IVinogradData";
import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";

class VinogradService{
    async deleteById(id: any) {
        var promise = await axiosInstance.delete("vineApp/vinogradi/vinograd",{params: {id}});
        return promise;
    }
    async getCokotiCount(id: any) {
        var promise = await axiosInstance.get("vineApp/vinogradi/loza-count",{params:id});
        return promise;
    }
   
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