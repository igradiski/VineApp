import axiosInstance from "../axios/axiosInstance";
import IFenofazaData from "../types/IFenofazaData";



class FenofazaService {
    constructor(){}

    async addFenofaza (data:IFenofazaData){
        var promise = await axiosInstance.post("vineApp/api/addFenofaza",data);
        return promise;
    }

}
export default FenofazaService;