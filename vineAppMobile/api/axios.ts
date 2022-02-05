import axios from "axios";

export const axiosInstance = axios.create({
    baseURL: "http://192.168.1.100:8080/",
    //baseURL: "https://vineapp-spring.herokuapp.com/",
    headers: {
      'Access-Control-Allow-Origin' : '/**',
    },

    //TODO INTERCEPTORI
  
  });