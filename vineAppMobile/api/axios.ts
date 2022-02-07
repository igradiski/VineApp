import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "http://192.168.1.100:8080/",
  //baseURL: "https://vineapp-spring.herokuapp.com/",
  headers: {
    "Access-Control-Allow-Origin": "/**",
  },
});
var store: any;

export const injectStore = (_store: any) => {
  store = _store;
};

axiosInstance.interceptors.request.use(async (request) => {
  const { accessToken } = store.getState().user;
  if (accessToken) {
    request.headers["Authorization"] = `Bearer ${accessToken}`;
  }
});
