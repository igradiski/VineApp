import axiosInstance from "../axios/axiosInstance";
import IUserRegisterData from "../types/userTypes";



class UserService{

    constructor() {
    }
    registerUser(data: IUserRegisterData){
        axiosInstance.post("vineApp/api/auth/signup",data)
        .then(response =>{
            console.log(response)
        });
    }

}

export default UserService;