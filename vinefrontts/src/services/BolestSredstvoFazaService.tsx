import axiosInstance from "../axios/axiosInstance";
import IBFSFilterData from "../types/IBFSFilterData";
import IDefaultPagingData from "../types/IDefaultPagingData";

class BolestSredstvoFazaService {

    async getBolestFaza(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/bolest-faza/sve-bolesti-faze", { params: data });
        return promise;
    }
    async getBolestSredstvo(data: IDefaultPagingData) {
        var promise = await axiosInstance.get("vineApp/bolest-sredstvo/sve-bolesti-sredstva", { params: data });
        return promise;
    }

    async getBolestFazaFiltered(data: IBFSFilterData) {
        var promise = await axiosInstance.get("vineApp/bolest-faza/sve-bolesti-faze-filter", { params: data });
        return promise;
    }

    async getBolestSredstvoFiltered(data: IBFSFilterData) {
        var promise = await axiosInstance.get("vineApp/bolest-sredstvo/sve-bolesti-sredstva-filter", { params: data });
        return promise;
    }

    async addBolestFaza(bolestId: string, fazaId: string) {

        var promise = await axiosInstance.put("vineApp/bolest-faza/bolesti-faze", {}, { params: { bolestId, fazaId } });
        return promise;
    }

    async addBolestSredstvo(bolestId: string, sredstvoId: string) {
        var promise = await axiosInstance.put("vineApp/bolest-sredstvo/bolesti-sredstva", {}, { params: { bolestId, sredstvoId } });
        return promise;
    }



}

export default BolestSredstvoFazaService;