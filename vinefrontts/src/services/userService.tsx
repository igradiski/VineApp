import axiosInstance from "../axios/axiosInstance";
import IUserRegisterData from "../types/userTypes";



class UserService{
    registerUser(data: IUserRegisterData){
        axiosInstance.post("vineApp/auth/signup",data)
        .then(response =>{
            console.log(response)
        });
    }

}

export default UserService;