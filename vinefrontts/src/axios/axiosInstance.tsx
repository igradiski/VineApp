import axios from "axios";
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