import axios from "axios";
import { axiosInstance } from "./axios";

export const registerUser = async (values:IUserData) =>{
    const response = await axiosInstance.post("vineApp/auth/signup",values);
    return response.data;
}

export const authUser = async (values:IUserData) =>{
    const response = await axiosInstance.post("vineApp/auth/signin",values);
    return response.data;
}