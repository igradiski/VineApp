import axiosInstance from "../axios/axiosInstance";
import IUserRegisterData from "../types/userTypes";



class UserService{
    async registerUser(data: IUserRegisterData){
        var promise = await  axiosInstance.post("vineApp/auth/signup",data)
        return promise;
    }

    async loginUser(data: IUserRegisterData){
        var promise = await axiosInstance.post("vineApp/auth/signin",data)
        return promise;
    }

}

export default UserService;