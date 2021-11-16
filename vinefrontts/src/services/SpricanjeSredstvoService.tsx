import axiosInstance from "../axios/axiosInstance";
import ISredstvoSpricanjeData from "../types/ISredstvoSpricanjeData";
import ISredstvaFilter from "./ISredstvaFilter";

class SpricanjeSredstvoService {
    async getOmjeri(sredstvoId:string,spricanjeId:string) {
        var promise = await axiosInstance.get("vineApp/spricanje-sredstvo/omjeri",{params:{sredstvoId,spricanjeId}});
        return promise;
    }

    async findBySpricanje(data: ISredstvaFilter) {
        var promise = await axiosInstance.get("vineApp/spricanje-sredstvo/sredstvo",{params: data});
        return promise;
    }
    
    async addSpricanjeSredstvo(data: ISredstvoSpricanjeData) {
        var promise = await axiosInstance.post("vineApp/spricanje-sredstvo/sredstvo",data);
        return promise;
    }

}

export default SpricanjeSredstvoService;