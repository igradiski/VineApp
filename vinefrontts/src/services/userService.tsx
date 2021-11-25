import axiosInstance from "../axios/axiosInstance";
import IUserRegisterData from "../types/userTypes";



class UserService{
    registerUser(data: IUserRegisterData){
        axiosInstance.post("vineApp/auth/signup",data)
        .then(response =>{
            console.log(response)
        });
    }

    async loginUser(data: IUserRegisterData){
        var promise = await axiosInstance.post("vineApp/auth/signin",data)
        return promise;
    }

}

export default UserService;