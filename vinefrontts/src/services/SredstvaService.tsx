import axiosInstance from "../axios/axiosInstance";
import ISredstvoData from "../types/ISredstvoType";



class SredstvaService{

    constructor() {
    }

    addSredstvo(data : ISredstvoData):void{
        console.log(data);
        axiosInstance.post("vineApp/api/addSredstvo",data).then(response =>{
            console.log(response)
        });
    }
}

export default SredstvaService;