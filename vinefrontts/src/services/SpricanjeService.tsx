import ISpricanjeData from "../types/ISpricanjeData";
import axiosInstance from "../axios/axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";

class SpricanjeService{
    
    async updateSpricanje(data: ISpricanjeData, id: string) {
        var promise = await axiosInstance.patch("vineApp/spricanja/spricanje",data,{params:{id}});
        return promise;
    }

    async deleteById(id: any) {
        var promise = await axiosInstance.delete("vineApp/spricanja/spricanje",{params: {id}});
        return promise;
    }

    async getSpricanja(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/spricanja/spricanja",{params:data});
        return promise;
    }

    async insertSpricanje(data: ISpricanjeData) {
        var promise = await axiosInstance.post("vineApp/spricanja/spricanje",data);
        return promise;
    }

}

export default SpricanjeService;