import axios from "axios";
import  constantsUI  from "../constantsUI/constantsUI";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    contentType: "application/json",
    Authorization: "Bearer " + localStorage.getItem("token"),
    "Cache-Control": "no-cache",
    "Access-Control-Allow-Origin": "*",
  },
});
export default axiosInstance;