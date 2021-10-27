import axiosInstance from "../axios/axiosInstance";

class BolestSredstvoFazaService{

    constructor(){};

    async addBolestFaza(bolestId: string, fazaId: string) {

        var promise = await axiosInstance.post("vineApp/bolest-faza/bolesti-faze",{params:{bolestId,fazaId}});
        return promise;

        
    }
    async addBolestSredstvo(bolestId: string, sredstvoId: string) {
        var promise = await axiosInstance.post("vineApp/bolest-sredstvo/bolesti-sredstva",{params:{bolestId,sredstvoId}});
        return promise;
    }

    
}

export default BolestSredstvoFazaService;