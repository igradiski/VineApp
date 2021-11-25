import axios from "axios";
import TokenService from "../feature/userLogin/TokenService";

const tokenService = new TokenService();

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    'Access-Control-Allow-Origin' : '/**',
  },

});

axiosInstance.interceptors.request.use(
  (config) => {
    var token = tokenService.getLocalAccessToken();
    if (token) {
      token = token.replace(/^"(.*)"$/, '$1');
      console.log(`Bearer ${token}`)
      config.headers["Authorization"] = `Bearer ${token}`;  // for Spring Boot back-end
      //config.headers["x-access-token"] = token; // for Node.js Express back-end
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (res) => {
    return res;
  },
  async (err) => {
    const originalConfig = err.config;

    if (originalConfig.url !== "vineApp/auth/signin" && err.response) {
      // Access Token was expired
      if (err.response.status === 401 && !originalConfig._retry) {
        originalConfig._retry = true;

        try {
          const rs = await axiosInstance.post("vineApp/auth/refreshToken", {
            refreshToken: tokenService.getLocalRefreshToken()
          });

          const { accessToken } = rs.data;
          tokenService.updateLocalAccessToken(accessToken);

          return axiosInstance(originalConfig);
        } catch (_error) {
          return Promise.reject(_error);
        }
      }
    }

    return Promise.reject(err);
  }
);
export default axiosInstance;